package com.ruoyi.web.controller.project;

import com.ruoyi.project.domain.ProjectInfo;
import com.ruoyi.project.service.impl.ProjectInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ProjectInfoController {
    private final ProjectInfoService projectInfoService;

    /**
     * 获取项目信息
     *
     * @author ruoyi
     */
//    @GetMapping("/project/info/getProjectInfo/{id}")
//    public ProjectInfo getProjectInfo(@PathVariable Long id)
//    {
//        return projectInfoService.selectProjectInfoById(id);
//    }

    @PostMapping("/project/info/updateProjectInfo/")
    public int updateProjectInfo(@RequestBody ProjectInfo projectInfo)
    {
        return projectInfoService.updateProjectInfo(projectInfo);
    }


}
