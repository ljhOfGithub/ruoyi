package com.ruoyi.web.controller.project;

import com.ruoyi.project.domain.ProjectInfo;
import com.ruoyi.project.service.impl.ProjectInfoService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ProjectBasicInfoController {
    private final ProjectInfoService projectInfoService;
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

    @GetMapping("/project/info/getProjectBasicInfo/list")
    public ProjectInfo listProjectInfo(@PathVariable Long id)
    {
        return projectInfoService.selectProjectInfoById(id);
    }


    @PostMapping("/project/info/updateProjectBasicInfo/")
    public int updateProjectInfo(@RequestBody ProjectInfo projectInfo)
    {
        return projectInfoService.updateProjectInfo(projectInfo);
    }


}