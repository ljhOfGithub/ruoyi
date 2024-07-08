package com.ruoyi.project.domain;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProjectBasicInfo {

    private String id;
    private String reporter_name;
    private String reporter_phone;
    private String firm_name;
    private String subordinate_name;
    private String officer;
    private String officer_phone;
    private String officer_firm_name;
    private String create_time;
    private String update_time;
    private String delete_time;


}