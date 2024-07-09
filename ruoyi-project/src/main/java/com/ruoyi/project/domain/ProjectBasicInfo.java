package com.ruoyi.project.domain;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectBasicInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    /**
     * 填报人
     */
    private String reporterName;

    /**
     * 填报人联系方式
     */
    private String reporterPhone;

    /**
     * 填报人公司
     */
    private String firmName;

    /**
     * 下属单位名称
     */
    private String subordinateName;

    /**
     * 责任人
     */
    private String officer;

    /**
     * 责任人联系方式
     */
    private String officerPhone;

    /**
     * 责任人单位名称
     */
    private String officerFirmName;

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

//    public ProjectBasicInfo() {}
}