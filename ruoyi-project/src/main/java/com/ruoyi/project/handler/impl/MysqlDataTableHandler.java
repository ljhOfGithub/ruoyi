package com.ruoyi.project.handler.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.project.constants.ColumnOperType;
import com.ruoyi.project.constants.DataType;
import com.ruoyi.project.constants.TableIndexType;
import com.ruoyi.project.constants.YesNoEnum;
import com.ruoyi.project.dto.TableStructureDto;
import com.ruoyi.project.exceptions.TenantInvestItemException;
import com.ruoyi.project.handler.SettingTableHandler;
import com.ruoyi.project.util.MysqlSqlFormatUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

public class MysqlDataTableHandler {
}

@Slf4j
@Service
@ConditionalOnProperty(value = "spring.monitordata.datasource.type", havingValue = "mysql")
public class MysqlModelDataTableHandler implements SettingTableHandler {

    @Resource
    @Qualifier("monitorDataDS")
    private DataSource dataSource;

    @Resource
    @Qualifier("monitorDataJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean createTable(TableStructureDto structure) {
        log.info("mysql库创建表");
        String tableName = structure.getTableName();
        String charset = StringUtils.isEmpty(structure.getCharset()) ? "utf8" : structure.getCharset();
        boolean hasTransaction = structure.isHasTransaction();
        String engineName = hasTransaction ? "INNODB" : "MyISAM";
        String tableComment = StringUtils.isEmpty(structure.getComment()) ? "''" : "'" + structure.getComment() + "'";

        String columns = getTableColumns(structure.getColumns());
        String indexs = getTableIndexs(structure.getIndexs());

        String sql = MysqlSqlFormatUtil.getCreateTableSql(tableName, columns, indexs, engineName, charset, tableComment);
        log.debug("创建表SQL:{}", sql);
        jdbcTemplate.execute(sql);
        return true;
    }

    private String getTableIndexs(List<TableStructureDto.IndexDto> indexs) {
        StringBuilder builder = new StringBuilder();
        int size = indexs.size();
        for (int i = 0; i < size; i++) {
            TableStructureDto.IndexDto indexDto = indexs.get(i);
            String indexColumns = indexDto.getColumnNames().stream().map(columnName -> "`" + columnName + "`").collect(Collectors.joining(",", "(", ")"));
            builder.append(transferIndexType(indexDto.getType())).append(" `").append(indexDto.getName()).append("` ").append(indexColumns).append(" USING BTREE");
            if (i != (size - 1)) {
                builder.append(",\n");
            }
        }
        return builder.toString();
    }

    private String transferIndexType(TableIndexType type) {
        switch (type) {
            case PRIMARY:
                return "PRIMARY KEY";
            case UNIQUE:
                return "UNIQUE KEY";
            case ORDINARY:
                return "KEY";
            default:
                throw new TenantInvestItemException("404");
        }
    }

    private String getTableColumns(List<TableStructureDto.ColumnDto> columns) {
        StringBuilder builder = new StringBuilder("\n");
        for (TableStructureDto.ColumnDto columnDto : columns) {
            builder.append(getColumnDefinition(columnDto));
            builder.append(",\n");
        }
        return builder.toString();
    }

    private String transferDataType(DataType dataType, Integer length) {
        switch (dataType) {
            case STRING:
                if (length == null) {
                    return "VARCHAR(200)";
                }
                if (length > 5000) {
                    return "TEXT";
                }
                return "VARCHAR(" + length + ")";
            case INT:
                if (length == null) {
                    return "INT(11)";
                } else if (length >= 0 && length <= 4) {
                    return "SMALLINT(" + length + ")";
                } else if (length>11){
                    return "BIGINT(20)";
                } else {
                    return "INT(" + length + ")";
                }
            case DOUBLE:
                if (length == null) {
                    return "DOUBLE";
                }
                return "DOUBLE(" + length + ")";
            case DATE:
                return "DATE";
            case DATETIME:
                return "DATETIME";
            case BOOLEAN:
                return "TINYINT(1)";
            default:
                throw new TenantInvestItemException("");
        }
    }

    @Override
    public void deleteTable(String tableName) {
        String deleteTableSql = MysqlSqlFormatUtil.getDeleteTableSql(tableName);
        try {
            jdbcTemplate.update(deleteTableSql);
        } catch (Exception e) {
            log.error("删除数据表异常", e);
            throw new TenantInvestItemException(e.getMessage());
        }
    }

    @Override
    public boolean isExist(String tableName) {
        String showTableSql = MysqlSqlFormatUtil.getQueryTableExistSql(getDbName(), tableName);
        final Long count = jdbcTemplate.queryForObject(showTableSql, Long.class);
        if (count != null && count == 1) {
            return true;
        }
        return false;
    }

    @Override
    public TableStructureDto getTableStructure(String tableName) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();
            String dbName = getDbName();
            String queryTableStructureSql = MysqlSqlFormatUtil.getQueryTableStructure(dbName, tableName);
            preparedStatement = connection.prepareStatement(queryTableStructureSql);
            ResultSet resultSet = preparedStatement.executeQuery();

            TableStructureDto tableStructureDto = TableStructureDto.builder().tableName(tableName).build();
            List<TableStructureDto.ColumnDto> columns = new ArrayList<>();
            tableStructureDto.setColumns(columns);
            while (resultSet.next()) {
                TableStructureDto.ColumnDto columnDto = new TableStructureDto.ColumnDto();
                columnDto.setName(resultSet.getString(1));
                columnDto.setLength((int) resultSet.getLong(3));
                columnDto.setDataType(transferDataType(resultSet.getString(2), columnDto.getLength()));
                String nullableStr = resultSet.getString(4);
                columnDto.setNullable("YES".equals(nullableStr));
                columnDto.setDefaultValue(resultSet.getString(5));
                columnDto.setComment(resultSet.getString(6));
                columns.add(columnDto);
            }
            return tableStructureDto;
        } catch (Exception e) {
            log.error("mysql库查询表结构时异常", e);
            throw new TenantInvestItemException(e.getMessage());
        } finally {
            try {
                connection.close();
                preparedStatement.close();
            } catch (Exception e) {
            }
        }
    }

    @Override
    public void modifyTableColumns(String tableName, Map<ColumnOperType, List<TableStructureDto.ColumnDto>> modifyColumns) {
        if (CollectionUtils.isEmpty(modifyColumns)) {
            return;
        }
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();) {
            List<TableStructureDto.ColumnDto> deleteColumns = modifyColumns.get(ColumnOperType.DELETE);
            if (!CollectionUtils.isEmpty(deleteColumns)) {
                for (TableStructureDto.ColumnDto columnDto : deleteColumns) {
                    String deleteSql = String.format(MysqlSqlFormatUtil.DELETE_COLUMN_PREPARE_SQL_FORMAT, tableName, columnDto.getName());
                    statement.addBatch(deleteSql);
                }
            }
            List<TableStructureDto.ColumnDto> updateColumns = modifyColumns.get(ColumnOperType.UPDATE);
            if (!CollectionUtils.isEmpty(updateColumns)) {
                for (TableStructureDto.ColumnDto columnDto : updateColumns) {
                    //删除字段
                    String deleteSql = MysqlSqlFormatUtil.getDeleteColumnSql(tableName, columnDto.getName());
                    //新增字段
                    String addSql = MysqlSqlFormatUtil.getAddColumnSql(tableName, getColumnDefinition(columnDto));
                    statement.addBatch(deleteSql);
                    statement.addBatch(addSql);
                }
            }
            List<TableStructureDto.ColumnDto> addColumns = modifyColumns.get(ColumnOperType.ADD);
            if (!CollectionUtils.isEmpty(addColumns)) {
                for (TableStructureDto.ColumnDto columnDto : addColumns) {
                    String columnDefinition = getColumnDefinition(columnDto);
                    String addSql = String.format(MysqlSqlFormatUtil.ADD_COLUMN_PREPARE_SQL_FORMAT, tableName, columnDefinition);
                    statement.addBatch(addSql);
                }
            }
            statement.executeBatch();
        } catch (SQLException e) {
            log.error("批量处理字段异常", e);
            throw new TenantInvestItemException(e.getMessage());
        }
    }

    private void deleteColumns(List<TableStructureDto.ColumnDto> deleteColumns, String tableName) {
        if (CollectionUtils.isEmpty(deleteColumns)) {
            return;
        }
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();) {
            for (TableStructureDto.ColumnDto columnDto : deleteColumns) {
                String deleteSql = String.format(MysqlSqlFormatUtil.DELETE_COLUMN_PREPARE_SQL_FORMAT, tableName, columnDto.getName());
                statement.addBatch(deleteSql);
            }
            statement.executeBatch();
        } catch (Exception e) {
            log.error("批量删除表字段异常", e);
            throw new TenantInvestItemException(e.getMessage());
        }
    }

    private void updateColumns(List<TableStructureDto.ColumnDto> updateColumns, String tableName) {
        if (CollectionUtils.isEmpty(updateColumns)) {
            return;
        }
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();) {
            for (TableStructureDto.ColumnDto columnDto : updateColumns) {
                //删除字段
                String deleteSql = MysqlSqlFormatUtil.getDeleteColumnSql(tableName, columnDto.getName());
                //新增字段
                String addSql = MysqlSqlFormatUtil.getAddColumnSql(tableName, getColumnDefinition(columnDto));
                statement.addBatch(deleteSql);
                statement.addBatch(addSql);
            }
            statement.executeBatch();
        } catch (Exception e) {
            log.error("批量更新表字段异常", e);
            throw new TenantInvestItemException(e.getMessage());
        }
    }

    private void addColumns(List<TableStructureDto.ColumnDto> addColumns, String tableName) {
        if (CollectionUtils.isEmpty(addColumns)) {
            return;
        }
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();) {
            for (TableStructureDto.ColumnDto columnDto : addColumns) {
                String columnDefinition = getColumnDefinition(columnDto);
                String addSql = String.format(MysqlSqlFormatUtil.ADD_COLUMN_PREPARE_SQL_FORMAT, tableName, columnDefinition);
                statement.addBatch(addSql);
            }
            statement.executeBatch();
        } catch (Exception e) {
            log.error("批量新增表字段异常", e);
            throw new TenantInvestItemException(e.getMessage());
        }
    }

    @Override
    public void insert(String tableName, LinkedHashMap<String, DataType> columnTypeMap, Map<String, Object> dataMap) {
        List<String> columnNames = new ArrayList<>(columnTypeMap.keySet());
        String columnsSqlPart = columnNames.stream().map(columnName -> "`" + columnName + "`").collect(Collectors.joining(","));
        //根据字段的顺序再把值拼好
        String valuesSqlPart = concatColumnValuesSqlPart(columnNames, columnTypeMap, dataMap);
        String insertSql = MysqlSqlFormatUtil.getInsertSql(tableName, columnsSqlPart, valuesSqlPart);
        jdbcTemplate.execute(insertSql);
    }

    private String concatColumnValuesSqlPart(List<String> columnNames, Map<String, DataType> columnTypeMap, Map<String, Object> dataMap) {
        StringBuilder valuesBuilder = new StringBuilder();
        for (String columnName : columnNames) {
            Object value = dataMap.get(columnName);
            if (value == null) {
                valuesBuilder.append("NULL,");
                continue;
            }
            DataType dataType = columnTypeMap.get(columnName);
            switch (dataType) {
                case INT:
                    long longValue = Long.parseLong(String.valueOf(value));
                    valuesBuilder.append(longValue).append(",");
                    break;
                case DOUBLE:
                    double doubleValue = Double.parseDouble(String.valueOf(value));
                    valuesBuilder.append(doubleValue).append(",");
                    break;
                case STRING:
                case DATE:
                case DATETIME:
                    String stringValue = String.valueOf(value);
                    valuesBuilder.append("'").append(stringValue).append("',");
                    break;
                case BOOLEAN:
                    int booleanValue = 0;
                    String booleanStrValue = String.valueOf(value);
                    if (Boolean.TRUE.toString().equals(booleanStrValue) || YesNoEnum.YES.getStrValue().equals(booleanStrValue)) {
                        booleanValue = 1;
                    }
                    valuesBuilder.append(booleanValue).append(",");
                    break;
                default:
                    throw new TenantInvestItemException("");
            }
        }
        String valuesSqlPart = valuesBuilder.toString();
        return valuesSqlPart.substring(0, valuesBuilder.length() - 1);
    }

    private String getColumnDefinition(TableStructureDto.ColumnDto columnDto) {
        StringBuilder builder = new StringBuilder();
        builder.append("`").append(columnDto.getName()).append("` ");
        String datatype = transferDataType(columnDto.getDataType(), columnDto.getLength());
        builder.append(datatype).append(" ");
        if (columnDto.getNullable() != null && columnDto.getNullable()) {
            builder.append("DEFAULT ").append(columnDto.getDefaultValue() == null ? "NULL " : "'" + columnDto.getDefaultValue() + "' ");
        } else {
            builder.append("NOT NULL ");
            if (columnDto.getDefaultValue() != null) {
                builder.append("DEFAULT '").append(columnDto.getDefaultValue()).append("' ");
            }
        }
        builder.append("COMMENT ").append(StringUtils.isEmpty(columnDto.getComment()) ? "''" : "'" + columnDto.getComment() + "'");
        return builder.toString();
    }

    private DataType transferDataType(String dbDataType, Integer length) {
        switch (dbDataType) {
            case "varchar":
                return DataType.STRING;
            case "smallint":
            case "int":
                return DataType.INT;
            case "double":
            case "float":
                return DataType.DOUBLE;
            case "date":
                return DataType.DATE;
            case "datetime":
                return DataType.DATETIME;
            case "tinyint":
                if (length != null && length == 1) {
                    return DataType.BOOLEAN;
                } else {
                    return DataType.INT;
                }
            default:
                return null;
        }
    }

    private String getDbName() {
        return jdbcTemplate.queryForObject("select DATABASE()", String.class);
    }


    @Override
    public <T> Page<T> queryPageData(String tableName, Map<String, DataType> columnTypeMap, String whereCondition,String orderBy, Map<String, String> columnMapping, int pageNum, int pageSize, Class<T> rowDataType) {
        long count = queryCount(tableName, whereCondition);
        Page<T> page = new Page<>(pageNum, pageSize, count);
        if (count == 0) {
            return page;
        }

        List<String> columnNames = new ArrayList<>(columnTypeMap.keySet());
        String queryColumns = columnNames.stream().map(name -> "`" + name + "`").collect(Collectors.joining(","));
        String queryPageSql = MysqlSqlFormatUtil.getQueryPageSql(tableName, queryColumns, whereCondition, pageNum, pageSize);
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(queryPageSql);) {
            ResultSet resultSet = statement.executeQuery();
            List<Map<String, Object>> dataList = new ArrayList<>();
            while (resultSet.next()) {
                Map<String, Object> rowDataMap = new HashMap<>();
                for (String columnName : columnNames) {
                    DataType dataType = columnTypeMap.get(columnName);
                    Object value = getColumnValue(resultSet, dataType, columnName);
                    if (columnMapping != null && columnMapping.containsKey(columnName)) {
                        String targetName = columnMapping.get(columnName);
                        rowDataMap.put(targetName, value);
                    } else {
                        rowDataMap.put(columnName, value);
                    }
                }
                dataList.add(rowDataMap);
            }
            String dataJson = JSON.toJSONString(dataList);
            List<T> records = JSON.parseArray(dataJson, rowDataType);
            page.setRecords(records);
            return page;
        } catch (Exception e) {
            log.error("查询分页数据异常", e);
            throw new TenantInvestItemException("查询分页数据异常");
        }
    }

    @Override
    public List<Map<String, Object>> executeQueryList(String sql) {
        try {
            return jdbcTemplate.queryForList(sql);
        } catch (Exception e) {
            log.error("查询分页数据异常", e);
            throw new TenantInvestItemException("查询分页数据异常");
        }
    }

    @Override
    public List<Map<String, Object>> queryPageData(String tableName, Map<String, DataType> columnTypeMap, String whereCondition, Map<String, String> columnMapping, int pageNum, int pageSize) {
        List<String> columnNames = new ArrayList<>(columnTypeMap.keySet());
        String queryColumns = columnNames.stream().map(name -> "`" + name + "`").collect(Collectors.joining(","));
        String queryPageSql = MysqlSqlFormatUtil.getQueryPageSql(tableName, queryColumns, whereCondition, pageNum, pageSize);
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(queryPageSql);) {
            ResultSet resultSet = statement.executeQuery();
            List<Map<String, Object>> dataList = new ArrayList<>();
            while (resultSet.next()) {
                Map<String, Object> rowDataMap = new HashMap<>();
                for (String columnName : columnNames) {
                    DataType dataType = columnTypeMap.get(columnName);
                    Object value = getColumnValue(resultSet, dataType, columnName);
                    if (columnMapping != null && columnMapping.containsKey(columnName)) {
                        String targetName = columnMapping.get(columnName);
                        rowDataMap.put(targetName, value);
                    } else {
                        rowDataMap.put(columnName, value);
                    }
                }
                dataList.add(rowDataMap);
            }
            return dataList;
        } catch (Exception e) {
            log.error("查询分页数据异常", e);
            throw new TenantInvestItemException("查询分页数据异常");
        }
    }


    @Override
    public List<Map<String, Object>> calculateRate(String tableName, String columnName, String whereCondition, long total) {
        String countSql = MysqlSqlFormatUtil.getCountByGroupSql(tableName, columnName, total, whereCondition);
        return jdbcTemplate.queryForList(countSql);
    }

    @Override
    public Map<String, Object> queryOneData(String tableName, Map<String, DataType> columnTypeMap, String whereCondition, Map<String, String> columnMapping) {
        List<String> columnNames = new ArrayList<>(columnTypeMap.keySet());
        String queryColumns = columnNames.stream().map(name -> "`" + name + "`").collect(Collectors.joining(","));
        String querySql = MysqlSqlFormatUtil.getQueryDataSql(tableName, queryColumns, whereCondition);
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(querySql);) {
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            Map<String, Object> rowDataMap = new HashMap<>();
            for (String columnName : columnNames) {
                DataType dataType = columnTypeMap.get(columnName);
                Object value = getColumnValue(resultSet, dataType, columnName);
                if (columnMapping != null && columnMapping.containsKey(columnName)) {
                    String targetName = columnMapping.get(columnName);
                    rowDataMap.put(targetName, value);
                } else {
                    rowDataMap.put(columnName, value);
                }
            }
            return rowDataMap;
        } catch (Exception e) {
            log.error("查询数据详情异常", e);
            throw new TenantInvestItemException("查询数据详情异常");
        }
    }

    @Override
    public <T> T queryOneData(String tableName, Map<String, DataType> columnTypeMap, String whereCondition, Map<String, String> columnMapping, Class<T> rowDataType) {
        Map<String, Object> rowDataMap = queryOneData(tableName, columnTypeMap, whereCondition, columnMapping);
        String dataJson = JSON.toJSONString(rowDataMap);
        return JSON.parseObject(dataJson, rowDataType);
    }

    @Override
    public <T> T queryOnce(String tableName, Map<String, DataType> columnTypeMap, String whereCondition, Map<String, String> columnMapping, Class<T> rowDataType) {
        List<String> columnNames = new ArrayList<>(columnTypeMap.keySet());
        String queryColumns = columnNames.stream().collect(Collectors.joining(","));
        String querySql = MysqlSqlFormatUtil.getQueryDataSql(tableName, queryColumns, whereCondition);
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(querySql);) {
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            Map<String, Object> rowDataMap = new HashMap<>();
            for (String columnName : columnNames) {
                DataType dataType = columnTypeMap.get(columnName);
                Object value = getColumnValue(resultSet, dataType, columnName);
                if (columnMapping != null && columnMapping.containsKey(columnName)) {
                    String targetName = columnMapping.get(columnName);
                    rowDataMap.put(targetName, value);
                } else {
                    rowDataMap.put(columnName, value);
                }
            }
            String dataJson = JSON.toJSONString(rowDataMap);
            return JSON.parseObject(dataJson, rowDataType);
        } catch (Exception e) {
            log.error("查询数据详情异常", e);
            throw new TenantInvestItemException("查询数据详情异常");
        }
    }

    @Override
    public List<Map<String, Object>> queryList(String tableName, Map<String, DataType> columnTypeMap, String whereCondition, Map<String, String> columnMapping) {
        List<String> columnNames = new ArrayList<>(columnTypeMap.keySet());
        String queryColumns = columnNames.stream().map(name -> "`" + name + "`").collect(Collectors.joining(","));
        String queryPageSql = MysqlSqlFormatUtil.getQueryListSql(tableName, queryColumns, whereCondition);
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(queryPageSql);) {
            ResultSet resultSet = statement.executeQuery();
            List<Map<String, Object>> dataList = new ArrayList<>();
            while (resultSet.next()) {
                Map<String, Object> rowDataMap = new HashMap<>();
                for (String columnName : columnNames) {
                    DataType dataType = columnTypeMap.get(columnName);
                    Object value = getColumnValue(resultSet, dataType, columnName);
                    if (columnMapping != null && columnMapping.containsKey(columnName)) {
                        String targetName = columnMapping.get(columnName);
                        rowDataMap.put(targetName, value);
                    } else {
                        rowDataMap.put(columnName, value);
                    }
                }
                dataList.add(rowDataMap);
            }
            return dataList;
        } catch (Exception e) {
            log.error("查询分页数据异常", e);
            throw new TenantInvestItemException("查询分页数据异常");
        }
    }

    public List<Map<String, Object>> queryListOnce(String tableName, Map<String, DataType> columnTypeMap, String whereCondition, Map<String, String> columnMapping) {
        List<String> columnNames = new ArrayList<>(columnTypeMap.keySet());
        String queryColumns = columnNames.stream().collect(Collectors.joining(","));
        String queryPageSql = MysqlSqlFormatUtil.getQueryListSql(tableName, queryColumns, whereCondition);
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(queryPageSql);) {
            ResultSet resultSet = statement.executeQuery();
            List<Map<String, Object>> dataList = new ArrayList<>();
            while (resultSet.next()) {
                Map<String, Object> rowDataMap = new HashMap<>();
                for (String columnName : columnNames) {
                    DataType dataType = columnTypeMap.get(columnName);
                    Object value = getColumnValue(resultSet, dataType, columnName);
                    if (columnMapping != null && columnMapping.containsKey(columnName)) {
                        String targetName = columnMapping.get(columnName);
                        rowDataMap.put(targetName, value);
                    } else {
                        rowDataMap.put(columnName, value);
                    }
                }
                dataList.add(rowDataMap);
            }
            return dataList;
        } catch (Exception e) {
            log.error("查询分页数据异常", e);
            throw new TenantInvestItemException("查询分页数据异常");
        }
    }


    @Override
    public long queryCount(String tableName, String whereCondition) {
        if (StringUtils.isEmpty(whereCondition)) {
            whereCondition = "";
        }
        String queryCountSql = MysqlSqlFormatUtil.getQueryCountSql(tableName, whereCondition);
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(queryCountSql);) {
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getLong(1);
        } catch (Exception e) {
            log.error("查询数据条数异常", e);
            throw new TenantInvestItemException("查询数据条数异常:" + e.getMessage());
        }
    }

    @Override
    public void batchInsert(String tableName, Map<String, DataType> dataTypeMap, List<List<Object>> rowDataList) {
        if (CollectionUtils.isEmpty(rowDataList) || CollectionUtils.isEmpty(dataTypeMap)) {
            return;
        }
        List<String> columnNames = new ArrayList<>(dataTypeMap.keySet());
        int rowValueNumber = rowDataList.get(0).size();
        if (columnNames.size() != rowValueNumber) {
            throw new TenantInvestItemException("待插入数据与其对应字段数量不一致");
        }
        String columnsSqlPart = columnNames.stream().map(name -> "`" + name + "`").collect(Collectors.joining(","));
        List<String> valueSqls = new ArrayList<>();
        for (List<Object> rowDatas : rowDataList) {
            String valueSql = "";
            for (int j = 0; j < rowValueNumber; j++) {
                Object value = rowDatas.get(j);
                if (value == null) {
                    valueSql = valueSql + "NULL";
                } else {
                    DataType dataType = dataTypeMap.get(columnNames.get(j));
                    if (dataType == DataType.STRING) {
                        valueSql = valueSql + "'" + value + "'";
                    } else if (dataType == DataType.DATE || dataType == DataType.DATETIME) {
                        if (value instanceof String) {
                            valueSql = valueSql + "'" + value + "'";
                        } else {
                            throw new TenantInvestItemException("暂不支持的日期数据格式");
                        }
                    } else {
                        valueSql = valueSql + value;
                    }
                }
                if (j < (rowValueNumber - 1)) {
                    valueSql = valueSql + ",";
                }
            }
            valueSqls.add(valueSql);
        }
        String batchInsertSql = MysqlSqlFormatUtil.getBatchInsertSql(tableName, columnsSqlPart, valueSqls);
        jdbcTemplate.execute(batchInsertSql);
    }

    @Override
    public void clearData(String tableName, String whereCondition) {
        String deleteDataSql = MysqlSqlFormatUtil.getDeleteDataSql(tableName, whereCondition);
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(deleteDataSql);) {
            statement.executeUpdate();
        } catch (Exception e) {
            log.error("删除数据异常", e);
            throw new TenantInvestItemException("删除数据异常:" + e.getMessage());
        }
    }

    @Override
    public void insert(String tableName, String insertSql) {
        jdbcTemplate.execute(insertSql);
    }

    @Override
    public List<Map<String, Object>> queryListWithAlias(String tableName, Map<String, DataType> columnTypeMap, List<String> fields, String whereCondition, int from, int size) {
        List<String> columnNames = new ArrayList<>(columnTypeMap.keySet());
        //查询字段的处理
        String queryColumns = getQueryColumns(fields);
        int pageNo = (from / size) + 1;
        String queryPageSql = MysqlSqlFormatUtil.getQueryPageSql(tableName, queryColumns, whereCondition, pageNo, size);
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(queryPageSql);) {
            ResultSet resultSet = statement.executeQuery();
            List<Map<String, Object>> dataList = new ArrayList<>();
            while (resultSet.next()) {
                Map<String, Object> rowDataMap = new HashMap<>();
                for (String columnName : columnNames) {
                    DataType dataType = columnTypeMap.get(columnName);
                    Object value = getColumnValue(resultSet, dataType, columnName);
                    rowDataMap.put(columnName, value);
                }
                dataList.add(rowDataMap);
            }
            return dataList;
        } catch (Exception e) {
            log.error("查询分页数据异常", e);
            throw new TenantInvestItemException("查询分页数据异常");
        }
    }

    private String getQueryColumns(List<String> fields) {
        List<String> finnalFields = new ArrayList<>();
        for (String field : fields) {
            if (field.contains(" as ") || field.contains(" AS ")) {
                finnalFields.add(field);
            } else {
                finnalFields.add("`" + field + "`");
            }
        }
        return String.join(",", finnalFields);
    }

    @Override
    public void renameTable(String originTableName, String newTableName) {

    }

    @Override
    public void copyData(String targetTableName, String sourceTableName, LinkedHashMap<String, String> fieldMapping, Boolean dropSource) {

    }

    @Override
    public void execute(String sql) {

    }

    @Override
    public Map<String, String> queryMaxAndMinValue(String tableName, String columnName, String whereCondition) {
        return null;
    }

    @Override
    public long queryTargetColumnUniqueCount(String tableName, String columnName, String whereCondition) {
        return 0;
    }

    @Override
    public List<String> queryDistinctValue(String tableName, String distinctColumnName, String whereCondition, Integer limit) {
        return null;
    }
}
