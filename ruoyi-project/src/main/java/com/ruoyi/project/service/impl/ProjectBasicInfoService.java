package com.ruoyi.project.service.impl;

import com.ruoyi.project.domain.ProjectBasicInfo;
import com.ruoyi.project.domain.ProjectInfo;
import com.ruoyi.project.mapper.ProjectBasicInfoMapper;
import com.ruoyi.project.mapper.ProjectInfoMapper;
import com.ruoyi.project.service.IProjectBasicInfoService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProjectBasicInfoService implements IProjectBasicInfoService {
    private final ProjectBasicInfoMapper projectBasicInfoMapper;

    @Override
    public ProjectBasicInfo selectProjectBasicInfoById(Long id) {
        return projectBasicInfoMapper.selectProjectBasicInfoByProjectId(id);
    }

    @Override
    public int updateProjectBasicInfo(ProjectBasicInfo projectBasicInfo) {
        return projectBasicInfoMapper.updateProjectBasicInfoByProject(projectBasicInfo);
    }
}
