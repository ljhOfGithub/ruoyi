package com.ruoyi.project.view;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class ReportContext {

    Long modelId;

    Long moniModelId;

    Long modelVersionId;

    /**
     * 报表Service
     */
    ReportViewService reportViewService;


    /**
     * 分组字段id
     */
    String groupVarId;
    /**
     * 分组变量
     */
//    MoniModelVar groupVar;
    /**
     * 分组值，最多5个
     */
    List<String>
            groupValues;

    /**
     * 分组辅助信息map，key为变量名称，
     * value仅在需要处理分箱时考虑，其他为[null,null,null]
     * value[0]为唯一值个数。value[1]为最大值。value[2]为最小值
     */
    Map<String, BigDecimal[]> binSplitInfoMap;

    /**
     * 表现数据的tag
     */
    String performanceTagId;

    /**
     * 策略报表的动态配置信息(动态配置的策略结果监控、策略规则命中报表)
     */

    private final String conditionPartSql = " 1=1 ";

}
