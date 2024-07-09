package com.ruoyi.project.view;

import com.ruoyi.project.constants.ReportCodeEnum;
import com.ruoyi.project.dto.ReportOutputDto;

import java.util.List;

public interface ReportViewService {

    ReportCodeEnum getType();
    default String getTitle(ReportContext context, String desc,boolean isChildReport){
        if (isChildReport){
            return desc+"-子表";
        }
        return desc+"主表";
    }
    List<ReportOutputDto> buildAllReports(ReportContext reportContext);


}
