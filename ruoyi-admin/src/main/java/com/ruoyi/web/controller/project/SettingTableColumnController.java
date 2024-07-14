package com.ruoyi.web.controller.project;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.project.entity.SettingTableColumn;
import com.ruoyi.project.mapper.SettingTableColumnMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

/**
 * @description setting_table_column控制器
 * @author lijihao
 * @date 2024-07-11
 */
@Slf4j
@RestController
@RequestMapping("/settingTableColumn")
public class SettingTableColumnController {

    @Autowired
    private SettingTableColumnMapper settingTableColumnMapper;

    /**
     * 新增或编辑
     */
    @PostMapping("/save")
    public Object save(@RequestBody SettingTableColumn settingTableColumn){
        log.info("settingTableColumn:"+JSON.toJSONString(settingTableColumn));
        SettingTableColumn oldSettingTableColumn = settingTableColumnMapper.selectOne(new QueryWrapper<SettingTableColumn>().eq("table_column_id",settingTableColumn.getTableColumnId()));
        settingTableColumn.setUpdateTime(new Date());
        if(oldSettingTableColumn!=null){
            settingTableColumnMapper.updateById(settingTableColumn);
        }else{
            if(settingTableColumnMapper.selectOne(new QueryWrapper<SettingTableColumn>().eq("table_column_name",settingTableColumn.getTableColumnName()))!=null){
                return AjaxResult.error("保存失败，名字重复");
            }
            settingTableColumn.setCreateTime(new Date());
            settingTableColumnMapper.insert(settingTableColumn);
        }
        return AjaxResult.success("保存成功");
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    public Object delete(int id){
        SettingTableColumn settingTableColumn = settingTableColumnMapper.selectOne(new QueryWrapper<SettingTableColumn>().eq("settingTableColumn_id",id));
        if(settingTableColumn!=null){
            settingTableColumnMapper.deleteById(id);
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
        SettingTableColumn settingTableColumn = settingTableColumnMapper.selectOne(new QueryWrapper<SettingTableColumn>().eq("settingTableColumn_id",id));
        if(settingTableColumn!=null){
            return AjaxResult.success(settingTableColumn);
        }else{
            return AjaxResult.error("没有找到该对象");
        }
    }

    @GetMapping("/list")
    public ModelAndView listPage(){
        return new ModelAndView("settingTableColumn-list");
    }

    @GetMapping("/edit")
    public ModelAndView editPage(int id){
        SettingTableColumn settingTableColumn = settingTableColumnMapper.selectOne(new QueryWrapper<SettingTableColumn>().eq("settingTableColumn_id",id));
        return new ModelAndView("settingTableColumn-edit","settingTableColumn",settingTableColumn);
    }




}