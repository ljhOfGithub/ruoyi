package com.ruoyi.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.project.domain.ProjectInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Mapper
public interface ProjectInfoMapper extends BaseMapper<ProjectInfo> {
    /**
     * 根据项目 id 查询项目信息
     * @param id
     * @return
     */
//    ProjectInfo selectProjectInfoByProjectId(Long id);
//
//    int updateProjectInfoByProject(ProjectInfo projectInfo);

}
