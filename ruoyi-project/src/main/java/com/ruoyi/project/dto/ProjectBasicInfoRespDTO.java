package com.ruoyi.project.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectBasicInfoRespDTO {
    private Long id;
    /**
     * 填报人
     */
    private String reporterName;

    /**
     * 填报人联系方式
     */
    private String reporterPhone;

    /**
     * 填报人公司
     */
    private String firmName;

    /**
     * 下属单位名称
     */
    private String subordinateName;

    /**
     * 责任人
     */
    private String officer;

    /**
     * 责任人联系方式
     */
    private String officerPhone;

    /**
     * 责任人单位名称
     */
    private String officerFirmName;
}
