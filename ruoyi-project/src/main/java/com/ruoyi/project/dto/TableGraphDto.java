package com.ruoyi.project.dto;

import lombok.*;
import lombok.experimental.Accessors;

import java.util.List;

public class TableGraphDto {

    
    private String xkey;

    
    private List<GraphConfig> datas;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Accessors(chain = true)

    public static class GraphConfig{
        
        private String label;
        
        private String value;
        
        private String color;
        
        private String type;
        
        private String maxType;
    }
}