package com.ruoyi.web.controller.project;

import com.ruoyi.project.domain.TenantInvestItem;
import com.ruoyi.project.service.TenantInvestItemService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @description tenant_invest_item
 * @author lijihao
 * @date 2024-07-09
 */
@RestController
@RequestMapping(value = "/tenantInvestItem")
public class TenantInvestItemController {

    @Resource
    private TenantInvestItemService tenantInvestItemService;

    /**
     * 新增
     * @author lijihao
     * @date 2024/07/09
     **/
    @RequestMapping("/insert")
    public Object insert(TenantInvestItem tenantInvestItem){
        return tenantInvestItemService.insert(tenantInvestItem);
    }

    /**
     * 刪除
     * @author lijihao
     * @date 2024/07/09
     **/
    @RequestMapping("/delete")
    public Object delete(int id){
        return tenantInvestItemService.delete(id);
    }

    /**
     * 更新
     * @author lijihao
     * @date 2024/07/09
     **/
    @RequestMapping("/update")
    public Object update(TenantInvestItem tenantInvestItem){
        return tenantInvestItemService.update(tenantInvestItem);
    }

    /**
     * 查询 根据主键 id 查询
     * @author lijihao
     * @date 2024/07/09
     **/
    @RequestMapping("/load")
    public Object load(int id){
        return tenantInvestItemService.load(id);
    }

    /**
     * 查询 分页查询
     * @author lijihao
     * @date 2024/07/09
     **/
    @RequestMapping("/pageList")
    public Map<String, Object> pageList(@RequestParam(required = false, defaultValue = "0") int offset,
                                        @RequestParam(required = false, defaultValue = "10") int pagesize) {
        return tenantInvestItemService.pageList(offset, pagesize);
    }

}