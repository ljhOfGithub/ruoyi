package com.ruoyi.project.constants;

public enum YesNoEnum {
    NO(0, "0", "否"),
    YES(1, "1", "是");

    private Integer value;
    private String strValue;
    private String desc;

    public static YesNoEnum getByEnumDesc(String desc) {
        YesNoEnum[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            YesNoEnum yesNoEnum = var1[var3];
            if (yesNoEnum.getDesc().equals(desc)) {
                return yesNoEnum;
            }
        }

        return null;
    }

    public static YesNoEnum getByValue(Integer value) {
        YesNoEnum[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            YesNoEnum yesNoEnum = var1[var3];
            if (yesNoEnum.getValue().equals(value)) {
                return yesNoEnum;
            }
        }

        return null;
    }

    public Integer getValue() {
        return this.value;
    }

    public String getStrValue() {
        return this.strValue;
    }

    public String getDesc() {
        return this.desc;
    }

    private YesNoEnum(Integer value, String strValue, String desc) {
        this.value = value;
        this.strValue = strValue;
        this.desc = desc;
    }
}
