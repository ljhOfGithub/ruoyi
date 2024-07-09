package com.ruoyi.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.project.domain.ProjectInfo;
import com.ruoyi.project.domain.TenantInvestItem;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description tenant_invest_item
 * @author lijihao
 * @date 2024-07-09
 */
@Mapper
@Repository
public interface TenantInvestItemMapper extends BaseMapper<TenantInvestItem> {

    /**
     * 新增
     * @author lijihao
     * @date 2024/07/09
     **/
    int insert(TenantInvestItem tenantInvestItem);

    /**
     * 刪除
     * @author lijihao
     * @date 2024/07/09
     **/
    int delete(int id);

    /**
     * 更新
     * @author lijihao
     * @date 2024/07/09
     **/
    int update(TenantInvestItem tenantInvestItem);

    /**
     * 查询 根据主键 id 查询
     * @author lijihao
     * @date 2024/07/09
     **/
    TenantInvestItem load(int id);

    /**
     * 查询 分页查询
     * @author lijihao
     * @date 2024/07/09
     **/
    List<TenantInvestItem> pageList(int offset, int pagesize);

    /**
     * 查询 分页查询 count
     * @author lijihao
     * @date 2024/07/09
     **/
    int pageListCount(int offset,int pagesize);

}
