package com.ruoyi.web.controller.project;

import com.ruoyi.project.entity.ProjectInfo;
import com.ruoyi.project.dto.ProjectInfoReqDTO;
import com.ruoyi.project.dto.ProjectInfoRespDTO;

import com.ruoyi.project.service.IProjectInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/project/basicInfo")
@RestController
@RequiredArgsConstructor
public class ProjectBasicInfoController {
    private final IProjectInfoService projectInfoService;
    /**
     * 获取项目信息
     *
     * @author ruoyi
     */
    @GetMapping("/getProjectBasicInfo/{id}")
    public ProjectInfoRespDTO getProjectInfo(@PathVariable Long id)
    {
        return projectInfoService.selectProjectInfoById(id);
    }

    @PostMapping("getProjectBasicInfoDetail")
    public ProjectInfoRespDTO getProjectBasicInfoDetail(@RequestBody ProjectInfoReqDTO projectInfoReqDTO)
    {
        return projectInfoService.getProjectBasicInfoDetail(projectInfoReqDTO);
    }

    @GetMapping("/listProjectBasicInfo")
    public List<ProjectInfo> listProjectInfo()
    {
        return projectInfoService.listProjectInfo();
    }


    @PostMapping("/updateProjectBasicInfo")
    public int updateProjectInfo(@RequestBody ProjectInfo projectInfo)
    {
        return projectInfoService.updateProjectInfo(projectInfo);
    }



}