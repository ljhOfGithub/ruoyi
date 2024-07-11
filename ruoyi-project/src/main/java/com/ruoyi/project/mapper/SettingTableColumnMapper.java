package com.ruoyi.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.ruoyi.project.entity.SettingTableColumn;
import java.util.List;
/**
 * @description setting_table_columnMapper
 * @author lijihao
 * @date 2024-07-11
 */
@Mapper
public interface SettingTableColumnMapper extends BaseMapper<SettingTableColumn> {

    @Select(
            "<script>select t0.* from setting_table_column t0 " +
                    //add here if need left join
                    "where 1=1" +
                    "<when test='settingTableId!=null and settingTableId!=&apos;&apos; '> and t0.setting_table_id=#{settingTableId}</when> " +
                    "<when test='settingTableName!=null and settingTableName!=&apos;&apos; '> and t0.setting_table_name=#{settingTableName}</when> " +
                    "<when test='settingTableCode!=null and settingTableCode!=&apos;&apos; '> and t0.setting_table_code=#{settingTableCode}</when> " +
                    "<when test='tableColumnId!=null and tableColumnId!=&apos;&apos; '> and t0.table_column_id=#{tableColumnId}</when> " +
                    "<when test='tableColumnName!=null and tableColumnName!=&apos;&apos; '> and t0.table_column_name=#{tableColumnName}</when> " +
                    "<when test='tableColumnCode!=null and tableColumnCode!=&apos;&apos; '> and t0.table_column_code=#{tableColumnCode}</when> " +
                    "<when test='func!=null and func!=&apos;&apos; '> and t0.func=#{func}</when> " +
                    "<when test='createTime!=null and createTime!=&apos;&apos; '> and t0.create_time=#{createTime}</when> " +
                    "<when test='updateTime!=null and updateTime!=&apos;&apos; '> and t0.update_time=#{updateTime}</when> " +
                    "<when test='deleteTime!=null and deleteTime!=&apos;&apos; '> and t0.delete_time=#{deleteTime}</when> " +
                    //add here if need page limit
                    //" limit ${page},${limit} " +
                    " </script>")
    List<SettingTableColumn> pageAll(SettingTableColumn queryParamDTO,int page,int limit);

    @Select("<script>select count(1) from setting_table_column t0 " +
            //add here if need left join
            "where 1=1" +
            "<when test='settingTableId!=null and settingTableId!=&apos;&apos; '> and t0.setting_table_id=#{settingTableId}</when> " +
            "<when test='settingTableName!=null and settingTableName!=&apos;&apos; '> and t0.setting_table_name=#{settingTableName}</when> " +
            "<when test='settingTableCode!=null and settingTableCode!=&apos;&apos; '> and t0.setting_table_code=#{settingTableCode}</when> " +
            "<when test='tableColumnId!=null and tableColumnId!=&apos;&apos; '> and t0.table_column_id=#{tableColumnId}</when> " +
            "<when test='tableColumnName!=null and tableColumnName!=&apos;&apos; '> and t0.table_column_name=#{tableColumnName}</when> " +
            "<when test='tableColumnCode!=null and tableColumnCode!=&apos;&apos; '> and t0.table_column_code=#{tableColumnCode}</when> " +
            "<when test='func!=null and func!=&apos;&apos; '> and t0.func=#{func}</when> " +
            "<when test='createTime!=null and createTime!=&apos;&apos; '> and t0.create_time=#{createTime}</when> " +
            "<when test='updateTime!=null and updateTime!=&apos;&apos; '> and t0.update_time=#{updateTime}</when> " +
            "<when test='deleteTime!=null and deleteTime!=&apos;&apos; '> and t0.delete_time=#{deleteTime}</when> " +
            " </script>")
    int countAll(SettingTableColumn queryParamDTO);

}