package com.ruoyi.project.service;

import com.ruoyi.project.entity.ProjectInfo;
import com.ruoyi.project.dto.ProjectInfoReqDTO;
import com.ruoyi.project.dto.ProjectInfoRespDTO;

import java.util.List;

public interface IProjectInfoService {
    /**
     * 查询项目基础信息
     * @param id
     * @return
     */
    ProjectInfoRespDTO selectProjectInfoById(Long id);

    int updateProjectInfo(ProjectInfo projectInfo);

    List<ProjectInfo> listProjectInfo();

    ProjectInfoRespDTO getProjectBasicInfoDetail(ProjectInfoReqDTO projectInfoReqDTO);

}
