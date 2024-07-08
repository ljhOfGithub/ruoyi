package com.ruoyi.project.mapper;

import com.ruoyi.project.domain.ProjectBasicInfo;

public interface ProjectBasicInfoMapper {
    /**
     * 根据项目 id 查询项目基础信息
     * @param id
     * @return
     */
    public ProjectBasicInfo selectProjectBasicInfoByProjectId(Long id);

    /**
     * 更新项目基础信息
     * @param projectBasicInfo
     * @return
     */
    public int updateProjectBasicInfoByProject(ProjectBasicInfo projectBasicInfo);

}
