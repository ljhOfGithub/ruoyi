package com.ruoyi.project.service;

import com.ruoyi.project.domain.TenantInvestItem;

import java.util.Map;

/**
 * @description tenant_invest_item
 * @author lijihao
 * @date 2024-07-09
 */
public interface TenantInvestItemService {

    /**
     * 新增
     */
    public Object insert(TenantInvestItem tenantInvestItem);

    /**
     * 删除
     */
    public Object delete(int id);

    /**
     * 更新
     */
    public Object update(TenantInvestItem tenantInvestItem);

    /**
     * 根据主键 id 查询
     */
    public TenantInvestItem load(int id);

    /**
     * 分页查询
     */
    public Map<String,Object> pageList(int offset, int pagesize);

}