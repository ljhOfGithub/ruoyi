package com.ruoyi.web.controller.project;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.project.entity.SettingTable;
import com.ruoyi.project.mapper.SettingTableMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
/**
 * @description setting_table控制器
 * @author lijihao
 * @date 2024-07-11
 */
@Slf4j
@RestController
@RequestMapping("/settingTable")
public class SettingTableController {

    @Autowired
    private SettingTableMapper settingTableMapper;

    /**
     * 新增或编辑
     */
    @PostMapping("/save")
    public Object save(@RequestBody SettingTable settingTable){
        log.info("settingTable:"+JSON.toJSONString(settingTable));
        SettingTable oldSettingTable = settingTableMapper.selectOne(new QueryWrapper<SettingTable>().eq("settingTable_id",settingTable.getSettingTableId()));
        settingTable.setUpdateTime(new Date());
        if(oldSettingTable!=null){
            settingTableMapper.updateById(settingTable);
        }else{
            if(settingTableMapper.selectOne(new QueryWrapper<SettingTable>().eq("settingTable_name",settingTable.getSettingTableName()))!=null){
                return AjaxResult.error("保存失败，名字重复");
            }
            settingTable.setCreateTime(new Date());
            settingTableMapper.insert(settingTable);
        }
        return AjaxResult.success("保存成功");
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    public Object delete(int id){
        SettingTable settingTable = settingTableMapper.selectOne(new QueryWrapper<SettingTable>().eq("settingTable_id",id));
        if(settingTable!=null){
            settingTableMapper.deleteById(id);
            return AjaxResult.success("删除成功");
        }else{
            return AjaxResult.error("没有找到该对象");
        }
    }

    /**
     * 查询
     */
    @PostMapping("/find")
    public Object find(int id){
        SettingTable settingTable = settingTableMapper.selectOne(new QueryWrapper<SettingTable>().eq("settingTable_id",id));
        if(settingTable!=null){
            return AjaxResult.success(settingTable);
        }else{
            return AjaxResult.error("没有找到该对象");
        }
    }



    @GetMapping("/list")
    public ModelAndView listPage(){
        return new ModelAndView("settingTable-list");
    }

    @GetMapping("/edit")
    public ModelAndView editPage(int id){
        SettingTable settingTable = settingTableMapper.selectOne(new QueryWrapper<SettingTable>().eq("settingTable_id",id));
        return new ModelAndView("settingTable-edit","settingTable",settingTable);
    }


}