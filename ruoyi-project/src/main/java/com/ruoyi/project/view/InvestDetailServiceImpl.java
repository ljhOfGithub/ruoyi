package com.ruoyi.project.view;

import com.ruoyi.project.constants.ReportCodeEnum;
import com.ruoyi.project.dto.ReportOutputDto;

import java.util.List;

public class InvestDetailServiceImpl implements ReportViewService{

    @Override
    public ReportCodeEnum getType() {
        return ReportCodeEnum.INVEST_DETAIL;
    }

    @Override
    public String getTitle(ReportContext context, String desc, boolean isChildReport) {
        return null;
    }

    @Override
    public List<ReportOutputDto> buildAllReports(ReportContext reportContext) {
        return null;
    }
}
