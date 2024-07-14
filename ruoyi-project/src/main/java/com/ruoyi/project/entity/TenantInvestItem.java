package com.ruoyi.project.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @description tenant_invest_item
 * @author BEJSON
 * @date 2024-07-09
 */
@Data
public class TenantInvestItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    /**
     * 租户id
     */
    private String tenantId;

    /**
     * 投入项名称 英文
     */
    private String itemName;

    /**
     * 投入项类型 0-数字 1-文本
     */
    private Integer itemType;

    /**
     * 设置类型 0-基本配置 1-自定义配置
     */
    private Integer settingType;

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

    /**
     * 项目 id
     */
    private String projectId;

    /**
     * 中文描述
     */
    private String itemDescription;

    /**
     * 投入项取值类型 0-文本 1-可选
     */
    private Integer itemValType;

    /**
     * 元数据对应的表格名称
     */
    private String itemTableName;

    public TenantInvestItem() {}
}