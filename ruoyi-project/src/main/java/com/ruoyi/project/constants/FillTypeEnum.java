package com.ruoyi.project.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum FillTypeEnum {
    AUTO_FILL("系统自动填写","auto_fill"),
    SELECT("下拉框","select"),
    AUTO_CAL("自动计算","auto_cal"),
    TEXT("手动输入","text");
    @Getter
    private String description;
    @Getter
    private String code;

}
