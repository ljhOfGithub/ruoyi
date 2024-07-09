package com.ruoyi.project.service.impl;

import com.ruoyi.project.domain.ProjectBasicInfo;
import com.ruoyi.project.mapper.ProjectBasicInfoMapper;
import com.ruoyi.project.service.IProjectBasicInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectBasicInfoService implements IProjectBasicInfoService {
    @Autowired
    private ProjectBasicInfoMapper projectBasicInfoMapper;

    @Override
    public ProjectBasicInfo selectProjectBasicInfoById(Long id) {
        return projectBasicInfoMapper.selectProjectBasicInfoByProjectId(id);
    }

    @Override
    public int updateProjectBasicInfo(ProjectBasicInfo projectBasicInfo) {
        return projectBasicInfoMapper.updateProjectBasicInfoByProject(projectBasicInfo);
    }
}
