package com.ruoyi.project.entity;

import lombok.Data;
import java.util.Date;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

/**
 * @description setting_table_column
 * @author lijihao
 * @date 2024-07-11
 */
@Data
public class SettingTableColumn implements Serializable {

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
     * 表格列 id
     */
    private String tableColumnId;

    /**
     * 表格列中文名
     */
    private String tableColumnName;

    /**
     * 表格列英文名
     */
    private String tableColumnCode;

    /**
     * 表格列函数 0-无函数 1-求和 2-百分数
     */
    private Integer func;

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

    public SettingTableColumn() {}
}