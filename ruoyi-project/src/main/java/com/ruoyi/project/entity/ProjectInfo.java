package com.ruoyi.project.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_project_info")
public class ProjectInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 项目 id
     */
    private Long projectId;

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

    /**
     * Create Time
     */
    private Date createTime;

    /**
     * Update Time
     */
    private Date updateTime;

    /**
     * Delete Time
     */
    private Date deleteTime;

//    public ProjectInfo() {}
}