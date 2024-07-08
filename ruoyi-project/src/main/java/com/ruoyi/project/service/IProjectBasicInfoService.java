package com.ruoyi.project.service;

import com.ruoyi.project.domain.ProjectBasicInfo;

public interface IProjectBasicInfoService {
    public ProjectBasicInfo selectProjectBasicInfoById(Long id);

    public int updateProjectBasicInfo(ProjectBasicInfo projectBasicInfo);
}
