package com.ruoyi.project.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectInfoReqDTO {
    private String id;
    private String project_type;
    private String project_code;
    private String project_name;
    private String single_evaluation_cycle;
    private String build_start_time;
    private String build_end_time;
    private String eval_start_time;
    private String eval_end_time;

}
