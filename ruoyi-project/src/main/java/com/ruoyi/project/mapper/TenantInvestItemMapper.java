package com.ruoyi.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.project.domain.TenantInvestItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.ruoyi.project.domain.TenantInvestItem;
import java.util.List;
/**
 * @description tenant_invest_itemMapper
 * @author lijihao
 * @date 2024-07-09
 */
@Mapper
public interface TenantInvestItemMapper extends BaseMapper<TenantInvestItem> {

    @Select(
            "<script>select t0.* from tenant_invest_item t0 " +
                    //add here if need left join
                    "where 1=1" +
                    "<when test='tenantId!=null and tenantId!=&apos;&apos; '> and t0.tenant_id=#{tenantId}</when> " +
                    "<when test='itemName!=null and itemName!=&apos;&apos; '> and t0.item_name=#{itemName}</when> " +
                    "<when test='itemType!=null and itemType!=&apos;&apos; '> and t0.item_type=#{itemType}</when> " +
                    "<when test='settingType!=null and settingType!=&apos;&apos; '> and t0.setting_type=#{settingType}</when> " +
                    "<when test='createTime!=null and createTime!=&apos;&apos; '> and t0.create_time=#{createTime}</when> " +
                    "<when test='updateTime!=null and updateTime!=&apos;&apos; '> and t0.update_time=#{updateTime}</when> " +
                    "<when test='deleteTime!=null and deleteTime!=&apos;&apos; '> and t0.delete_time=#{deleteTime}</when> " +
                    "<when test='projectId!=null and projectId!=&apos;&apos; '> and t0.project_id=#{projectId}</when> " +
                    "<when test='itemDescription!=null and itemDescription!=&apos;&apos; '> and t0.item_description=#{itemDescription}</when> " +
                    "<when test='itemValType!=null and itemValType!=&apos;&apos; '> and t0.item_val_type=#{itemValType}</when> " +
                    //add here if need page limit
                    //" limit ${page},${limit} " +
                    " </script>")
    List<TenantInvestItem> pageAll(TenantInvestItem queryParamDTO,int page,int limit);

    @Select("<script>select count(1) from tenant_invest_item t0 " +
            //add here if need left join
            "where 1=1" +
            "<when test='tenantId!=null and tenantId!=&apos;&apos; '> and t0.tenant_id=#{tenantId}</when> " +
            "<when test='itemName!=null and itemName!=&apos;&apos; '> and t0.item_name=#{itemName}</when> " +
            "<when test='itemType!=null and itemType!=&apos;&apos; '> and t0.item_type=#{itemType}</when> " +
            "<when test='settingType!=null and settingType!=&apos;&apos; '> and t0.setting_type=#{settingType}</when> " +
            "<when test='createTime!=null and createTime!=&apos;&apos; '> and t0.create_time=#{createTime}</when> " +
            "<when test='updateTime!=null and updateTime!=&apos;&apos; '> and t0.update_time=#{updateTime}</when> " +
            "<when test='deleteTime!=null and deleteTime!=&apos;&apos; '> and t0.delete_time=#{deleteTime}</when> " +
            "<when test='projectId!=null and projectId!=&apos;&apos; '> and t0.project_id=#{projectId}</when> " +
            "<when test='itemDescription!=null and itemDescription!=&apos;&apos; '> and t0.item_description=#{itemDescription}</when> " +
            "<when test='itemValType!=null and itemValType!=&apos;&apos; '> and t0.item_val_type=#{itemValType}</when> " +
            " </script>")
    int countAll(TenantInvestItem queryParamDTO);

}