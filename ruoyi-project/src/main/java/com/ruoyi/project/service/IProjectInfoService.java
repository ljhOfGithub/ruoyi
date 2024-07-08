package com.ruoyi.project.service;

import com.ruoyi.project.domain.ProjectInfo;

import java.util.List;

public interface IProjectInfoService {
    /**
     * 查询项目基础信息
     * @param id
     * @return
     */
    ProjectInfo selectProjectInfoById(Long id);

    int updateProjectInfo(ProjectInfo projectInfo);

    List<ProjectInfo> listProjectInfo();

}
