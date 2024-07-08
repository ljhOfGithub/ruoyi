package com.ruoyi.project.service.impl;

import com.ruoyi.project.domain.ProjectInfo;
import com.ruoyi.project.mapper.ProjectInfoMapper;
import com.ruoyi.project.service.IProjectInfoService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProjectInfoService implements IProjectInfoService {

    private final ProjectInfoMapper projectInfoMapper;

    @Override
    public ProjectInfo selectProjectInfoById(Long id) {
        return projectInfoMapper.selectProjectInfoByProjectId(id);
    }

    @Override
    public int updateProjectInfo(ProjectInfo projectInfo) {
        return projectInfoMapper.updateProjectInfoByProject(projectInfo);
    }


}
