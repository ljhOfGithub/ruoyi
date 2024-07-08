package com.ruoyi.project.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectInfoBasicReqDTO {
    private String id;
    private String reporter_name;
    private String reporter_phone;
    private String firm_name;
    private String subordinate_name;
    private String officer;
    private String officer_phone;
    private String officer_firm_name;
}
