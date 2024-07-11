package com.ruoyi.project.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.ruoyi.project.entity.SettingTable;
import java.util.List;
/**
 * @description setting_tableMapper
 * @author lijihao
 * @date 2024-07-11
 */
@Mapper
public interface SettingTableMapper extends BaseMapper<SettingTable> {

    @Select(
            "<script>select t0.* from setting_table t0 " +
                    //add here if need left join
                    "where 1=1" +
                    "<when test='setting_table_id!=null and setting_table_id!=&apos;&apos; '> and t0.setting_table_id=#{setting_table_id}</when> " +
                    "<when test='setting_table_name!=null and setting_table_name!=&apos;&apos; '> and t0.setting_table_name=#{setting_table_name}</when> " +
                    "<when test='setting_table_code!=null and setting_table_code!=&apos;&apos; '> and t0.setting_table_code=#{setting_table_code}</when> " +
                    "<when test='create_time!=null and create_time!=&apos;&apos; '> and t0.create_time=#{create_time}</when> " +
                    "<when test='update_time!=null and update_time!=&apos;&apos; '> and t0.update_time=#{update_time}</when> " +
                    "<when test='delete_time!=null and delete_time!=&apos;&apos; '> and t0.delete_time=#{delete_time}</when> " +
                    //add here if need page limit
                    //" limit ${page},${limit} " +
                    " </script>")
    List<SettingTable> pageAll(SettingTable queryParamDTO,int page,int limit);

    @Select("<script>select count(1) from setting_table t0 " +
            //add here if need left join
            "where 1=1" +
            "<when test='setting_table_id!=null and setting_table_id!=&apos;&apos; '> and t0.setting_table_id=#{setting_table_id}</when> " +
            "<when test='setting_table_name!=null and setting_table_name!=&apos;&apos; '> and t0.setting_table_name=#{setting_table_name}</when> " +
            "<when test='setting_table_code!=null and setting_table_code!=&apos;&apos; '> and t0.setting_table_code=#{setting_table_code}</when> " +
            "<when test='create_time!=null and create_time!=&apos;&apos; '> and t0.create_time=#{create_time}</when> " +
            "<when test='update_time!=null and update_time!=&apos;&apos; '> and t0.update_time=#{update_time}</when> " +
            "<when test='delete_time!=null and delete_time!=&apos;&apos; '> and t0.delete_time=#{delete_time}</when> " +
            " </script>")
    int countAll(SettingTable queryParamDTO);

}