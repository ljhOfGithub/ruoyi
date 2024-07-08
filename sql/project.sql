CREATE TABLE `sys_project_basic_info` (
                                          `id` int NOT NULL AUTO_INCREMENT COMMENT 'Primary Key',
                                          `reporter_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '填报人',
                                          `reporter_phone` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '填报人联系方式',
                                          `firm_name` varchar(64) DEFAULT NULL COMMENT '填报人公司',
                                          `subordinate_name` varchar(64) DEFAULT NULL COMMENT '下属单位名称',
                                          `officer` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '责任人',
                                          `officer_phone` varchar(32) DEFAULT NULL COMMENT '责任人联系方式',
                                          `officer_firm_name` varchar(64) DEFAULT NULL COMMENT '责任人单位名称',
                                          `create_time` datetime DEFAULT NULL COMMENT 'Create Time',
                                          `update_time` datetime DEFAULT NULL COMMENT 'Update Time',
                                          `delete_time` datetime DEFAULT NULL COMMENT 'Delete Time',
                                          PRIMARY KEY (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 2 DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '项目基础信息表'

CREATE TABLE `sys_project_info` (
                                    `id` int NOT NULL AUTO_INCREMENT COMMENT 'Primary Key',
                                    `project_type` bigint DEFAULT NULL COMMENT '项目类型',
                                    `project_code` varchar(32) DEFAULT NULL COMMENT '项目编号',
                                    `project_name` varchar(64) DEFAULT NULL COMMENT '项目名称',
                                    `single_evaluation_cycle` int DEFAULT NULL COMMENT '单批资产评估周期',
                                    `build_start_time` datetime DEFAULT NULL COMMENT '项目建设开始时间',
                                    `build_end_time` datetime DEFAULT NULL COMMENT '项目建设结束时间',
                                    `eval_start_time` datetime DEFAULT NULL COMMENT '项目评估开始时间',
                                    `eval_end_time` datetime DEFAULT NULL COMMENT '项目评估结束时间',
                                    `create_time` datetime DEFAULT NULL COMMENT 'Create Time',
                                    `update_time` datetime DEFAULT NULL COMMENT 'Update Time',
                                    `delete_time` datetime DEFAULT NULL COMMENT 'Delete Time',
                                    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '项目信息表'