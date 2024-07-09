package com.ruoyi.project.domain;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_project_info")
public class ProjectInfo {
    private Long id;
    private String project_type;
    private String project_code;
    private String project_name;
    private String single_evaluation_cycle;
    private String build_start_time;
    private String build_end_time;
    private String eval_start_time;
    private String eval_end_time;
    private Date create_time;
    private Date update_time;
    private Date delete_time;

}