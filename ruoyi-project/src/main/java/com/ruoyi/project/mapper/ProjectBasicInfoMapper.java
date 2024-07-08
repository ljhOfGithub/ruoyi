package com.ruoyi.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.project.domain.ProjectBasicInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Mapper
public interface ProjectBasicInfoMapper extends BaseMapper<ProjectBasicInfo> {
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
