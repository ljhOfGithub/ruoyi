package com.ruoyi.project.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ReportCodeEnum {
    INVEST_DETAIL("投资明细","invest_detail"),
    INCOME_DETAIL("收入明细","income_detail"),
    EXPENDITURE_DETAIL("支出明细","expenditure_detail"),
    DEPRECIATION("折旧摊销","depreciation"),
    CASH_STREAM("现金流","cash_stream"),
    COMPREHENSIVE_EVALUATION("综合评估","comprehensive_evaluation");
    @Getter
    private String description;
    @Getter
    private String code;

}
