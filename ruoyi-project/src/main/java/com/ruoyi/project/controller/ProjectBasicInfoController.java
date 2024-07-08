package com.ruoyi.project.controller;

import com.ruoyi.project.domain.ProjectInfo;
import com.ruoyi.project.service.IProjectInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ProjectBasicInfoController {
    private final IProjectInfoService projectInfoService;

    /**
     * 获取项目信息
     *
     * @author ruoyi
     */
    @GetMapping("/project/info/getProjectBasicInfo/{id}")
    public ProjectInfo getProjectInfo(@PathVariable Long id)
    {
        return projectInfoService.selectProjectInfoById(id);
    }

    @PostMapping("/project/info/updateProjectBasicInfo/")
    public int updateProjectInfo(@RequestBody ProjectInfo projectInfo)
    {
        return projectInfoService.updateProjectInfo(projectInfo);
    }


}