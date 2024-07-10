package com.ruoyi.project.handler;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.project.constants.ColumnOperType;
import com.ruoyi.project.constants.DataType;
import com.ruoyi.project.dto.TableStructureDto;
import com.ruoyi.project.exceptions.TenantInvestItemException;
import com.ruoyi.project.util.LocalDateTimeUtil;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public interface SettingTableHandler {

    boolean createTable(TableStructureDto structure);

    void deleteTable(String tableName);

    /**
     * 判断表是否存在
     * @param tableName
     * @return
     */
    boolean isExist(String tableName);

    TableStructureDto getTableStructure(String tableName);

    void modifyTableColumns(String tableName, Map<ColumnOperType,List<TableStructureDto.ColumnDto>> modifyColumns);

    void insert(String tableName, LinkedHashMap<String, DataType> columnTypeMap, Map<String, Object> dataMap);

    <T> Page<T> queryPageData(String tableName, Map<String, DataType> columnTypeMap, String whereCondition, String orderBy, Map<String,String> columnMapping, int pageNum, int pageSize, Class<T> rowDataType);

    List<Map<String, Object>> executeQueryList(String sql);

    List<Map<String,Object>> queryPageData(String tableName, Map<String,DataType> columnTypeMap, String whereCondition,Map<String,String> columnMapping, int pageNum, int pageSize);


    List<Map<String, Object>> calculateRate(String tableName, String columnName, String whereCondition, long total);

    <T> T queryOneData(String tableName, Map<String, DataType> columnTypeMap, String whereCondition, Map<String, String> columnMapping, Class<T> dataType);

    Map<String,Object> queryOneData(String tableName, Map<String, DataType> columnTypeMap, String whereCondition, Map<String, String> columnMapping);

    <T> T queryOnce(String tableName, Map<String, DataType> columnTypeMap, String whereCondition, Map<String, String> columnMapping, Class<T> dataType);

    List<Map<String, Object>> queryList(String tableName, Map<String, DataType> columnTypeMap, String whereCondition, Map<String, String> columnMapping);

    List<Map<String, Object>> queryListOnce(String tableName, Map<String, DataType> columnTypeMap, String whereCondition, Map<String, String> columnMapping);

    default Object getColumnValue(ResultSet resultSet, DataType dataType, String columnName) {
        try {
            switch (dataType){
                case INT:
                    return resultSet.getLong(columnName);
                case DOUBLE:
                    return resultSet.getDouble(columnName);
                case DATE:
                    Date date = resultSet.getDate(columnName);
                    if (date==null){
                        return null;
                    }
                    return LocalDateTimeUtil.parse(date,"yyyy-MM-dd");
                case DATETIME:
                    Timestamp timestamp = resultSet.getTimestamp(columnName);
                    if (timestamp==null){
                        return null;
                    }
                    return LocalDateTimeUtil.parse(timestamp.toLocalDateTime(),"yyyy-MM-dd HH:mm:ss");
                case STRING:
                    return resultSet.getString(columnName);
                case BOOLEAN:
                    return resultSet.getBoolean(columnName);
                default:
                    throw new TenantInvestItemException("异常");
            }
        }catch (Exception e){
            throw new TenantInvestItemException("从查询结果中取值异常:"+e.getMessage());
        }
    }

    void clearData(String tableName, String whereCondition);

    /**
     * 按照sql插入数据，这个sql可以是批量插入数据sql
     * @param tableName
     * @param insertSql
     */
    void insert(String tableName, String insertSql);

    List<Map<String, Object>> queryListWithAlias(String tableName,Map<String,DataType> columnTypeMap, List<String> fields, String s, int from, int size);

    long queryCount(String tableName, String whereCondition);

    /**
     * 批量插入数据
     * @param tableName 表名
     * @param dataTypeMap 待插入数据字段与类型映射
     * @param rowDataList 数据列表，数据的顺序和待插入字段的顺序必须一致
     */
    void batchInsert(String tableName, Map<String, DataType> dataTypeMap, List<List<Object>> rowDataList);

    /**
     * 数据表重命名
     * @param originTableName
     * @param newTableName
     */
    void renameTable(String originTableName, String newTableName);

    /**
     * 表数据拷贝
     * @param targetTableName 目标表名称
     * @param sourceTableName 源数据表名称
     * @param fieldMapping  映射关系
     * @param dropSource  是否删除源表
     */
    void copyData(String targetTableName, String sourceTableName, LinkedHashMap<String, String> fieldMapping,Boolean dropSource);

    /**
     * 执行无返回结果的SQL（如创建表、更新字段等）
     * @param sql
     */
    void execute(String sql);

    /**
     * 查询指定目标字段的最大最小值
     * @param tableName
     * @param columnName
     * @param whereCondition
     * @return
     */
    Map<String, String> queryMaxAndMinValue(String tableName, String columnName, String whereCondition);


    /**
     * 查询指定目标字段的唯一值个数
     * @param tableName
     * @param columnName
     * @param whereCondition
     * @return
     */
    long queryTargetColumnUniqueCount(String tableName, String columnName, String whereCondition);

    /**
     * 查询目标字段的唯一值
     * @param tableName
     * @param distinctColumnName
     * @param whereCondition
     * @return
     */
    List<String> queryDistinctValue(String tableName, String distinctColumnName, String whereCondition,Integer limit);
}