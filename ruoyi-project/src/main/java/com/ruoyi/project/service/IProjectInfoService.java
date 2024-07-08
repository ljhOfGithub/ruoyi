package com.ruoyi.project.service;

import com.ruoyi.project.domain.ProjectInfo;

public interface IProjectInfoService {
    /**
     * 查询项目基础信息
     * @param id
     * @return
     */
    public ProjectInfo selectProjectInfoById(Long id);

    public int updateProjectInfo(ProjectInfo projectInfo);
}
