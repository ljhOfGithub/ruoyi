package com.ruoyi.project.mapper;

import com.ruoyi.project.domain.ProjectInfo;

public interface ProjectInfoMapper {
    /**
     * 根据项目 id 查询项目信息
     * @param id
     * @return
     */
    public ProjectInfo selectProjectInfoByProjectId(Long id);

    public int updateProjectInfoByProject(ProjectInfo projectInfo);



}
