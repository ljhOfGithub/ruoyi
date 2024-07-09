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
public class ProjectInfoReqDTO {
    private Long id;
    /**
     * 项目类型
     */
    private Long projectType;

    /**
     * 项目编号
     */
    private String projectCode;

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 单批资产评估周期
     */
    private Integer singleEvaluationCycle;

    /**
     * 项目建设开始时间
     */
    private Date buildStartTime;

    /**
     * 项目建设结束时间
     */
    private Date buildEndTime;

    /**
     * 项目评估开始时间
     */
    private Date evalStartTime;

    /**
     * 项目评估结束时间
     */
    private Date evalEndTime;

}
