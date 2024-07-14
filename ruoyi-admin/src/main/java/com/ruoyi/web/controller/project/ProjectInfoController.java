package com.ruoyi.web.controller.project;

import com.ruoyi.project.entity.ProjectInfo;
import com.ruoyi.project.dto.ProjectInfoRespDTO;
import com.ruoyi.project.service.IProjectInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/project/info")
public class ProjectInfoController {
    private final IProjectInfoService projectInfoService;

    /**
     * 获取项目信息
     *
     * @author ruoyi
     */
    @GetMapping("getProjectInfo/{id}")
    public ProjectInfoRespDTO getProjectInfo(@PathVariable Long id)
    {
        return projectInfoService.selectProjectInfoById(id);
    }

    @PostMapping("updateProjectInfo/")
    public int updateProjectInfo(@RequestBody ProjectInfo projectInfo)
    {
        return projectInfoService.updateProjectInfo(projectInfo);
    }


}
