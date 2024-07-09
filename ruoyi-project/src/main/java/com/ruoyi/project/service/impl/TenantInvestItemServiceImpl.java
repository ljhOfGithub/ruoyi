package com.ruoyi.project.service.impl;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.project.domain.TenantInvestItem;
import com.ruoyi.project.mapper.TenantInvestItemMapper;
import com.ruoyi.project.service.TenantInvestItemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description tenant_invest_item
 * @author lijihao
 * @date 2024-07-09
 */
@Service
public class TenantInvestItemServiceImpl implements TenantInvestItemService {

    @Resource
    private TenantInvestItemMapper tenantInvestItemMapper;


    @Override
    public Object insert(TenantInvestItem tenantInvestItem) {

        // valid
        if (tenantInvestItem == null) {
            return AjaxResult.error("必要参数缺失");
        }

        tenantInvestItemMapper.insert(tenantInvestItem);
        return AjaxResult.success();
    }


    @Override
    public Object delete(int id) {
        int ret = tenantInvestItemMapper.delete(id);
        return ret>0?AjaxResult.success():AjaxResult.error();
    }


    @Override
    public Object update(TenantInvestItem tenantInvestItem) {
        int ret = tenantInvestItemMapper.update(tenantInvestItem);
        return ret>0?AjaxResult.success():AjaxResult.error();
    }


    @Override
    public TenantInvestItem load(int id) {
        return tenantInvestItemMapper.load(id);
    }


    @Override
    public Map<String,Object> pageList(int offset, int pagesize) {

        List<TenantInvestItem> pageList = tenantInvestItemMapper.pageList(offset, pagesize);
        int totalCount = tenantInvestItemMapper.pageListCount(offset, pagesize);

        // result
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("pageList", pageList);
        result.put("totalCount", totalCount);

        return result;
    }

}