package com.ruoyi.project.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

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
     * 投入项名称
     */
    private String itemName;

    /**
     * 投入项类型 0-数字 1-文本
     */
    private String itemType;

    /**
     * 设置类型 0-基本配置 1-自定义配置
     */
    private String settingType;

    /**
     * Create Time
     */
    private String createTime;

    /**
     * Update Time
     */
    private String updateTime;

    /**
     * Delete Time
     */
    private String deleteTime;

    public TenantInvestItem() {}
}
