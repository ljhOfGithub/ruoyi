package com.ruoyi.web.controller.project;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.project.domain.TenantInvestItem;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.ruoyi.project.mapper.TenantInvestItemMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.servlet.ModelAndView;
import java.util.Date;

/**
 * @description tenant_invest_item控制器
 * @author lijihao
 * @date 2024-07-09
 */
@Slf4j
@RestController
@RequestMapping("/tenantInvestItem")
public class TenantInvestItemController {

    @Autowired
    private TenantInvestItemMapper tenantInvestItemMapper;

    /**
     * 新增或编辑
     */
    @PostMapping("/save")
    public Object save(@RequestBody TenantInvestItem tenantInvestItem){
        log.info("tenantInvestItem:"+JSON.toJSONString(tenantInvestItem));
        TenantInvestItem oldTenantInvestItem = tenantInvestItemMapper.selectOne(new QueryWrapper<TenantInvestItem>().eq("tenantInvestItem_id",tenantInvestItem.getTenantId()));
        tenantInvestItem.setUpdateTime(new Date());
        if(oldTenantInvestItem!=null){
            tenantInvestItemMapper.updateById(tenantInvestItem);
        }else{
            if(tenantInvestItemMapper.selectOne(new QueryWrapper<TenantInvestItem>().eq("tenantInvestItem_name",tenantInvestItem.getItemName()))!=null){
                return AjaxResult.error("保存失败，名字重复");
            }
            tenantInvestItem.setCreateTime(new Date());
            tenantInvestItemMapper.insert(tenantInvestItem);
        }
        return AjaxResult.success("保存成功");
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    public Object delete(int id){
        TenantInvestItem tenantInvestItem = tenantInvestItemMapper.selectOne(new QueryWrapper<TenantInvestItem>().eq("tenantInvestItem_id",id));
        if(tenantInvestItem!=null){
            tenantInvestItemMapper.deleteById(id);
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
        TenantInvestItem tenantInvestItem = tenantInvestItemMapper.selectOne(new QueryWrapper<TenantInvestItem>().eq("tenantInvestItem_id",id));
        if(tenantInvestItem!=null){
            return AjaxResult.success(tenantInvestItem);
        }else{
            return AjaxResult.error("没有找到该对象");
        }
    }

    /**
     * 自动分页查询
     */
    @PostMapping("/list")
    public Object list(String searchParams,
                       @RequestParam(required = false, defaultValue = "0") int page,
                       @RequestParam(required = false, defaultValue = "10") int limit) {
        log.info("page:"+page+"-limit:"+limit+"-json:"+ JSON.toJSONString(searchParams));
        //分页构造器
        Page<TenantInvestItem> buildPage = new Page<TenantInvestItem>(page,limit);
        //条件构造器
        QueryWrapper<TenantInvestItem> queryWrapper = new QueryWrapper<TenantInvestItem>();
        if(StringUtils.isNotEmpty(searchParams)&&JSON.isValid(searchParams)) {
            TenantInvestItem tenantInvestItem = JSON.parseObject(searchParams, TenantInvestItem.class);
            queryWrapper.eq(StringUtils.isNoneEmpty(tenantInvestItem.getItemName()), "tenantInvestItem_name", tenantInvestItem.getItemName());
        }
        //执行分页
        IPage<TenantInvestItem> pageList = tenantInvestItemMapper.selectPage(buildPage, queryWrapper);
        //返回结果
//        return AjaxResult.success.PAGE(pageList.getRecords(),pageList.getTotal());
        return AjaxResult.success(pageList.getRecords());

    }
    @GetMapping("/list")
    public ModelAndView listPage(){
        return new ModelAndView("tenantInvestItem-list");
    }

    @GetMapping("/edit")
    public ModelAndView editPage(int id){
        TenantInvestItem tenantInvestItem = tenantInvestItemMapper.selectOne(new QueryWrapper<TenantInvestItem>().eq("tenantInvestItem_id",id));
        return new ModelAndView("tenantInvestItem-edit","tenantInvestItem",tenantInvestItem);
    }


}
