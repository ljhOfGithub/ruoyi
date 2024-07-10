package com.ruoyi.project.dto;

import com.ruoyi.project.constants.DataType;
import com.ruoyi.project.constants.TableIndexType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author ycc
 * @Date 2024/4/7 19:28
 * @Desc 数据表结构定义
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TableStructureDto {
    /**
     * 数据表名称
     */
    private String tableName;
    /**
     * 使用的字符集
     */
    private String charset;
    /**
     * 是否使用事务
     */
    private boolean hasTransaction = false;
    /**
     * 表注释
     */
    private String comment;
    /**
     * 表字段信息
     */
    private List<ColumnDto> columns;
    /**
     * 索引信息
     */
    private List<IndexDto> indexs;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ColumnDto{
        /**
         * 字段名称
         */
        private String name;
        /**
         * 数据类型（所有数据归类为系统所支持的几大类型）
         */
        private DataType dataType;
        /**
         * 数据在数据表中的真实的存储类型
         */
        private String trulyType;
        /**
         * 字段长度（对于字符串、数字类型有效）
         */
        private Integer length;
        /**
         * 是否可为空
         */
        private Boolean nullable;
        /**
         * 默认值
         */
        private String defaultValue;
        /**
         * 字段描述
         */
        private String comment;
    }

    @Data
    @Builder
    public static class IndexDto{
        /**
         * 索引名称
         */
        private String name;
        /**
         * 索引类型
         */
        private TableIndexType type;
        /**
         * 索引关联的字段名称
         */
        private List<String> columnNames;
        /**
         * 索引描述
         */
        private String comment;

        public IndexDto() {
        }

        public IndexDto(String name, TableIndexType type, List<String> columnNames) {
            this.name = name;
            this.type = type;
            this.columnNames = columnNames;
        }

        public IndexDto(String name, TableIndexType type, List<String> columnNames, String comment) {
            this.name = name;
            this.type = type;
            this.columnNames = columnNames;
            this.comment = comment;
        }
    }
}
