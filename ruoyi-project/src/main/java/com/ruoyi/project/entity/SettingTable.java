package com.ruoyi.project.entity;

import lombok.Data;
import java.util.Date;
import java.util.List;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

/**
 * @description setting_table
 * @author lijihao
 * @date 2024-07-11
 */
@Data
public class SettingTable implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    /**
     * 表格 id
     */
    private String settingTableId;

    /**
     * 表格中文名
     */
    private String settingTableName;

    /**
     * 表格英文名
     */
    private String settingTableCode;

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

    public SettingTable() {}
}