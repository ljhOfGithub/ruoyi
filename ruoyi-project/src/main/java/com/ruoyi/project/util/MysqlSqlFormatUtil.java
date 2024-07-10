package com.ruoyi.project.util;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

public class MysqlSqlFormatUtil {

    private static final String QUERY_TABLE_EXIST_SQL_FORMAT =
            "SELECT COUNT(1) FROM information_schema.`TABLES` WHERE TABLE_SCHEMA='%s' AND table_name='%s'";
    private static final String QUERY_TABLE_STRUCTURE_SQL_FORMAT = "SELECT \n" + "column_name,\n" + "data_type,\n"
            + "character_maximum_length,\n" + "is_nullable,\n" + "column_default,\n" + "column_comment \n"
            + "FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = '%s' AND TABLE_NAME = '%s'";

    // 创建表的sql模板，参数依次为表名称、字段定义、索引定义、引擎名称、字符集设置、注释
    private static final String CREATE_TABLE_SQL_FORMAT =
            "create table `%s`(%s %s)ENGINE=%s default charset=%s comment=%s";
    private static final String INSERT_DATA_SQL_FORMAT = "insert into `%s`(%s) values(%s)";
    private static final String QUERY_COUNT_SQL_FORMAT = "select count(1) from `%s` %s";
    private static final String QUERY_PAGE_SQL_FORMAT = "select %s from `%s` %s limit %d,%d";
    private static final String QUERY_LIST_SQL_FORMAT = "select %s from `%s` %s";
    private static final String QUERY_DATA_SQL_FORMAT = "select %s from `%s` %s";
    private static final String DELETE_DATA_SQL_FORMAT = "delete from `%s` %s";
    public static final String ADD_COLUMN_PREPARE_SQL_FORMAT = "ALTER TABLE `%s` ADD COLUMN %s";
    public static final String DELETE_COLUMN_PREPARE_SQL_FORMAT = "ALTER TABLE `%s` DROP COLUMN `%s`";

    public static String getQueryTableExistSql(String databaseName, String tableName) {
        return String.format(QUERY_TABLE_EXIST_SQL_FORMAT, databaseName, tableName);
    }

    public static String getCreateTableSql(String tableName, String columns, String indexs, String engineName,
                                           String charset, String comment) {
        return String.format(CREATE_TABLE_SQL_FORMAT, tableName, columns, indexs, engineName, charset, comment);
    }

    public static String getQueryTableStructure(String dbName, String tableName) {
        return String.format(QUERY_TABLE_STRUCTURE_SQL_FORMAT, dbName, tableName);
    }

    public static String getInsertSql(String tableName, String columnsSqlPart, String valuesSqlPart) {
        return String.format(INSERT_DATA_SQL_FORMAT, tableName, columnsSqlPart, valuesSqlPart);
    }

    public static String getQueryCountSql(String tableName, String whereCondition) {
        return String.format(QUERY_COUNT_SQL_FORMAT, tableName, whereCondition);
    }

    public static String getQueryPageSql(String tableName, String queryColumns, String whereCondition, int pageNum, int pageSize) {
        pageNum = Math.max(pageNum, 1);
        int start = (pageNum - 1) * pageSize;
        if (!StringUtils.isEmpty(whereCondition) && !whereCondition.contains("where ") && !whereCondition.contains("WHERE ")){
            whereCondition = "where " + whereCondition;
        }
        return String.format(QUERY_PAGE_SQL_FORMAT, queryColumns, tableName, whereCondition, start, pageSize);
    }

    public static String getQueryListSql(String tableName, String queryColumns, String whereCondition) {
        if (!StringUtils.isEmpty(whereCondition) && !whereCondition.contains("where ") && !whereCondition.contains("WHERE ")) {
            whereCondition = "where " + whereCondition;
        }
        return String.format(QUERY_LIST_SQL_FORMAT, queryColumns, tableName, whereCondition);
    }

    public static String getQueryDataSql(String tableName, String queryColumns, String whereCondition) {
        return String.format(QUERY_DATA_SQL_FORMAT, queryColumns, tableName, whereCondition);
    }

    public static String getDeleteDataSql(String tableName, String whereCondition) {
        return String.format(DELETE_DATA_SQL_FORMAT, tableName, whereCondition);
    }

    public static String getDeleteColumnSql(String tableName, String columnName) {
        return String.format("ALTER TABLE `%s` DROP COLUMN `%s`", tableName, columnName);
    }

    public static String getAddColumnSql(String tableName, String columnDefinition) {
        return String.format("ALTER TABLE `%s` ADD COLUMN %s", tableName, columnDefinition);
    }

    public static String getDeleteTableSql(String tableName) {
        return String.format("DROP TABLE `%s`", tableName);
    }

    public static String getBatchInsertSql(String tableName, String columnsSqlPart, List<String> valueSqls) {
        if (CollectionUtils.isEmpty(valueSqls)){
            return null;
        }
        String values = valueSqls.stream().map(s-> "("+s+")").collect(Collectors.joining(","));
        return String.format("INSERT INTO `%s`(%s) values %s", tableName, columnsSqlPart, values);
    }

    public static String getSelectAndInsertSql(String tableName, String selectSQL) {
        if (StringUtils.isEmpty(selectSQL)) {
            return null;
        }
        return String.format("create TEMPORARY TABLE `%s` as  %s", tableName, selectSQL);
    }

    public static String getCountByGroupSql(String tableName, String columnName, Long total, String whereCondition) {
        if (StringUtils.isEmpty(columnName)) {
            return null;
        }
        return String.format("SELECT %s,  COUNT(%s) AS count_of_value,  ROUND(COUNT(%s) / %s,10) AS percentage FROM `%s` %s GROUP BY %s", columnName, columnName, columnName, total, tableName, whereCondition, columnName);
    }
}