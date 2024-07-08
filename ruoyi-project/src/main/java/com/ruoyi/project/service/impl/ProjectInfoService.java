package com.ruoyi.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.project.domain.ProjectInfo;
import com.ruoyi.project.dto.ProjectInfoReqDTO;
import com.ruoyi.project.dto.ProjectInfoRespDTO;
import com.ruoyi.project.mapper.ProjectInfoMapper;
import com.ruoyi.project.service.IProjectInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectInfoService implements IProjectInfoService {

    private final ProjectInfoMapper projectInfoMapper;

    @Override
    public ProjectInfoRespDTO selectProjectInfoById(Long id) {
        LambdaQueryWrapper<ProjectInfo> lambdaQueryWrapper = Wrappers.lambdaQuery(ProjectInfo.class)
                .eq(ProjectInfo::getId, id);
        ProjectInfo projectInfo = projectInfoMapper.selectOne(lambdaQueryWrapper);
        return ProjectInfoRespDTO
                .builder()
                .project_code(projectInfo.getProject_code())
                .project_type(projectInfo.getProject_type())
                .project_name(projectInfo.getProject_name())
                .build();
    }

    @Override
    public List<ProjectInfo> listProjectInfo() {
        LambdaQueryWrapper<ProjectInfo> lambdaQueryWrapper = Wrappers.lambdaQuery(ProjectInfo.class);
        return projectInfoMapper.selectList(lambdaQueryWrapper);
    }

    @Override
    public ProjectInfoRespDTO getProjectBasicInfoDetail(ProjectInfoReqDTO projectInfoReqDTO) {
        return null;
    }

    @Override
    public int updateProjectInfo(ProjectInfo newProjectInfo) {
        LambdaUpdateWrapper<ProjectInfo> lambdaUpdateWrapper = Wrappers.lambdaUpdate(ProjectInfo.class)
                .eq(ProjectInfo::getId, newProjectInfo.getId())
                .eq(ProjectInfo::getProject_code, newProjectInfo.getProject_code());
        ProjectInfo projectInfo = new ProjectInfo();
        return projectInfoMapper.update(projectInfo, lambdaUpdateWrapper);
    }


}
