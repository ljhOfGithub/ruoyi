package com.ruoyi.project.dto;

import lombok.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ReportOutputDto {
    private int order;

    private Table table;

    private Map<String, List<Report>> reportMaps;

    public static class Report {

    }

    public static class Table extends Report {

        private boolean link;


        private String anchor;
        /**
         * 类型
         */
        
        private String type = "table";

        
        private Boolean focusOnDiagonal = false;
        /**
         * 标题
         */
        
        private Text title;


        
        private String tableName;

        
        private String subTableName;

        /**
         * 合并单元格
         */
        
        private List<MergeCell> mergeCells;

        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        @Builder
        
        public static class Text {
            /**
             * 标题
             */
            
            private String text;
        }

        /**
         * headers
         */
        
        private List<Header> headers;
        /**
         * datas
         */
        
        private List<?> datas;
        /**
         * 当前满足条件总行数
         */
        
        private long total;
        /**
         * 每页显示条数
         */
        
        private long size;
        /**
         * 当前页
         */
        
        private long currentNo;

        /**
         * 当前分页总页数
         */
        
        private long pages;

        @Data
        
        public static class Header {

            
            private boolean link;
            /**
             * label
             */
            
            private String label;

            /**
             * value
             */
            
            private String value;

            /**
             * fixed
             */
            
            private String fixed;

            /**
             * children
             */
            
            private List<Header> children;

            /**
             * key
             */
            
            private String key;

            /**
             * 表格中包含图形数据的图形定义
             */
            
            private TableGraphDto graph;

            /**
             * 所有graph图datas项里，有一个存在负数标记为true
             */
            
            private Boolean minusStateOut;


            public Header() {
            }

            public Header(String label) {
                this.label = label;
            }

            public Header(String label,String value) {
                this.label = label;
                this.value = value;
            }
            public Header(String label,String value,TableGraphDto graph) {
                this.label = label;
                this.value = value;
                this.graph = graph;
            }

            public Header(String label, String value, String fixed, List<Header> children) {
                this.label = label;
                this.value = value;
                this.fixed = fixed;
                this.children = children;
            }
            public Header(String label, String value, String fixed, List<Header> children,boolean link) {
                this.label = label;
                this.value = value;
                this.fixed = fixed;
                this.children = children;
                this.link = link;
            }


            public static List<Header> generateSimpleHeadList(List<String> names) {
                return names.stream().map(name -> new Header(name, name))
                        .collect(Collectors.toList());
            }

            public static List<Header> generateSimpleHeadList(List<String> keys, List<String> names) {
                return IntStream.range(0, keys.size())
                        .mapToObj(i -> new Header(keys.get(i), names.get(i)))
                        .collect(Collectors.toList());
            }
        }

        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        @Builder
        public static class MergeCell {
            /**
             * 第N行
             */
            private int row;

            /**
             * 第N列
             */
            private int col;

            /**
             * 合并行数
             */
            private int rowspan;

            /**
             * 合并列数
             */
            private int colspan;
        }

    }
}
