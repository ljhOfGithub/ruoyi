package com.ruoyi.web.controller.project;

import com.ruoyi.project.domain.ProjectInfo;
import com.ruoyi.project.dto.ProjectInfoRespDTO;
import com.ruoyi.project.service.impl.ProjectInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/project/info")
public class ProjectInfoController {
    private final ProjectInfoService projectInfoService;

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
