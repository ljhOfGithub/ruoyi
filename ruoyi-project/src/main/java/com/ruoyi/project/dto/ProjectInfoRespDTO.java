package com.ruoyi.project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectInfoRespDTO {
    private Long id;
    private String project_type;
    private String project_code;
    private String project_name;
    private String single_evaluation_cycle;
    private Date build_start_time;
    private Date build_end_time;
    private Date eval_start_time;
    private Date eval_end_time;
}
