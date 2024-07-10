package com.ruoyi.project.dto;


import lombok.*;

import java.util.List;

/**
 * 动态建表dto
 */
@Data
public class CreateTabDTO {

    /**
     * 表名
     */
    private String tableName;

    /**
     * 字段列表
     */
    private List<DbColumn> columns;

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class DbColumn {

        /**
         * 字段列名
         */
        private String columnName;

        /**
         * 字段类型
         */
        private String columnDataType;

        /**
         * 是否可以为空
         */
        private Boolean allowNull = true;

        /**
         * 是否索引列
         */
        private Boolean isIndex;

    }
}