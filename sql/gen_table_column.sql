create table gen_table_column
(
    column_id      bigint auto_increment comment '编号'
        primary key,
    table_id       bigint null comment '归属表编号',
    column_name    varchar(200) null comment '列名称',
    column_comment varchar(500) null comment '列描述',
    column_type    varchar(100) null comment '列类型',
    java_type      varchar(500) null comment 'JAVA类型',
    java_field     varchar(200) null comment 'JAVA字段名',
    is_pk          char null comment '是否主键（1是）',
    is_increment   char null comment '是否自增（1是）',
    is_required    char null comment '是否必填（1是）',
    is_insert      char null comment '是否为插入字段（1是）',
    is_edit        char null comment '是否编辑字段（1是）',
    is_list        char null comment '是否列表字段（1是）',
    is_query       char null comment '是否查询字段（1是）',
    query_type     varchar(200) default 'EQ' null comment '查询方式（等于、不等于、大于、小于、范围）',
    html_type      varchar(200) null comment '显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）',
    dict_type      varchar(200) default '' null comment '字典类型',
    sort           int null comment '排序',
    create_by      varchar(64)  default '' null comment '创建者',
    create_time    datetime null comment '创建时间',
    update_by      varchar(64)  default '' null comment '更新者',
    update_time    datetime null comment '更新时间'
) comment '代码生成业务表字段';

INSERT INTO ruoyi.gen_table_column (column_id, table_id, column_name, column_comment, column_type, java_type,
                                    java_field, is_pk, is_increment, is_required, is_insert, is_edit, is_list, is_query,
                                    query_type, html_type, dict_type, sort, create_by, create_time, update_by,
                                    update_time)
VALUES (14, 2, 'id', '主键ID', 'int(11)', 'Long', 'id', '1', '1', '0', '1', null, null, null, 'EQ', 'input', '', 1,
        'admin', '2023-12-26 21:56:56', '', '2023-12-26 22:00:11');
INSERT INTO ruoyi.gen_table_column (column_id, table_id, column_name, column_comment, column_type, java_type,
                                    java_field, is_pk, is_increment, is_required, is_insert, is_edit, is_list, is_query,
                                    query_type, html_type, dict_type, sort, create_by, create_time, update_by,
                                    update_time)
VALUES (15, 2, 'time', '申请时间', 'datetime', 'Date', 'time', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'datetime', '',
        2, 'admin', '2023-12-26 21:56:56', '', '2023-12-26 22:00:11');
INSERT INTO ruoyi.gen_table_column (column_id, table_id, column_name, column_comment, column_type, java_type,
                                    java_field, is_pk, is_increment, is_required, is_insert, is_edit, is_list, is_query,
                                    query_type, html_type, dict_type, sort, create_by, create_time, update_by,
                                    update_time)
VALUES (16, 2, 'user_name', '游戏名称', 'varchar(256)', 'String', 'userName', '0', '0', '0', '1', '1', '1', '1', 'LIKE',
        'input', '', 3, 'admin', '2023-12-26 21:56:56', '', '2023-12-26 22:00:11');
INSERT INTO ruoyi.gen_table_column (column_id, table_id, column_name, column_comment, column_type, java_type,
                                    java_field, is_pk, is_increment, is_required, is_insert, is_edit, is_list, is_query,
                                    query_type, html_type, dict_type, sort, create_by, create_time, update_by,
                                    update_time)
VALUES (17, 2, 'user_uuid', null, 'varchar(256)', 'String', 'userUuid', '0', '0', '0', '1', '1', '1', '1', 'EQ',
        'input', '', 4, 'admin', '2023-12-26 21:56:57', '', '2023-12-26 22:00:11');
INSERT INTO ruoyi.gen_table_column (column_id, table_id, column_name, column_comment, column_type, java_type,
                                    java_field, is_pk, is_increment, is_required, is_insert, is_edit, is_list, is_query,
                                    query_type, html_type, dict_type, sort, create_by, create_time, update_by,
                                    update_time)
VALUES (18, 2, 'online_flag', '正版标识', 'int(11)', 'Long', 'onlineFlag', '0', '0', '0', '1', '1', '1', '1', 'EQ',
        'input', '', 5, 'admin', '2023-12-26 21:56:57', '', '2023-12-26 22:00:11');
INSERT INTO ruoyi.gen_table_column (column_id, table_id, column_name, column_comment, column_type, java_type,
                                    java_field, is_pk, is_increment, is_required, is_insert, is_edit, is_list, is_query,
                                    query_type, html_type, dict_type, sort, create_by, create_time, update_by,
                                    update_time)
VALUES (19, 2, 'qq_num', '用户QQ号', 'varchar(64)', 'String', 'qqNum', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'input',
        '', 6, 'admin', '2023-12-26 21:56:57', '', '2023-12-26 22:00:11');
INSERT INTO ruoyi.gen_table_column (column_id, table_id, column_name, column_comment, column_type, java_type,
                                    java_field, is_pk, is_increment, is_required, is_insert, is_edit, is_list, is_query,
                                    query_type, html_type, dict_type, sort, create_by, create_time, update_by,
                                    update_time)
VALUES (20, 2, 'remark', '描述', 'text', 'String', 'remark', '0', '0', '0', '1', '1', '1', null, 'EQ', 'textarea', '',
        7, 'admin', '2023-12-26 21:56:57', '', '2023-12-26 22:00:11');
INSERT INTO ruoyi.gen_table_column (column_id, table_id, column_name, column_comment, column_type, java_type,
                                    java_field, is_pk, is_increment, is_required, is_insert, is_edit, is_list, is_query,
                                    query_type, html_type, dict_type, sort, create_by, create_time, update_by,
                                    update_time)
VALUES (21, 2, 'review_users', '审核用户', 'varchar(256)', 'String', 'reviewUsers', '0', '0', '0', '1', '1', '1', '1',
        'EQ', 'input', '', 8, 'admin', '2023-12-26 21:56:57', '', '2023-12-26 22:00:11');
INSERT INTO ruoyi.gen_table_column (column_id, table_id, column_name, column_comment, column_type, java_type,
                                    java_field, is_pk, is_increment, is_required, is_insert, is_edit, is_list, is_query,
                                    query_type, html_type, dict_type, sort, create_by, create_time, update_by,
                                    update_time)
VALUES (22, 2, 'status', '审核状态', 'varchar(32)', 'String', 'status', '0', '0', '0', '1', '1', '1', '1', 'EQ',
        'radio', '', 9, 'admin', '2023-12-26 21:56:57', '', '2023-12-26 22:00:11');
INSERT INTO ruoyi.gen_table_column (column_id, table_id, column_name, column_comment, column_type, java_type,
                                    java_field, is_pk, is_increment, is_required, is_insert, is_edit, is_list, is_query,
                                    query_type, html_type, dict_type, sort, create_by, create_time, update_by,
                                    update_time)
VALUES (23, 2, 'add_state', '添加状态', 'varchar(64)', 'String', 'addState', '0', '0', '0', '1', '1', '1', '1', 'EQ',
        'input', '', 10, 'admin', '2023-12-26 21:56:57', '', '2023-12-26 22:00:11');
INSERT INTO ruoyi.gen_table_column (column_id, table_id, column_name, column_comment, column_type, java_type,
                                    java_field, is_pk, is_increment, is_required, is_insert, is_edit, is_list, is_query,
                                    query_type, html_type, dict_type, sort, create_by, create_time, update_by,
                                    update_time)
VALUES (24, 2, 'add_time', '添加时间', 'datetime', 'Date', 'addTime', '0', '0', '0', '1', '1', '1', '1', 'EQ',
        'datetime', '', 11, 'admin', '2023-12-26 21:56:57', '', '2023-12-26 22:00:11');
INSERT INTO ruoyi.gen_table_column (column_id, table_id, column_name, column_comment, column_type, java_type,
                                    java_field, is_pk, is_increment, is_required, is_insert, is_edit, is_list, is_query,
                                    query_type, html_type, dict_type, sort, create_by, create_time, update_by,
                                    update_time)
VALUES (25, 2, 'remove_reason', '移除原因', 'text', 'String', 'removeReason', '0', '0', '0', '1', '1', '1', '1', 'EQ',
        'textarea', '', 12, 'admin', '2023-12-26 21:56:57', '', '2023-12-26 22:00:11');
INSERT INTO ruoyi.gen_table_column (column_id, table_id, column_name, column_comment, column_type, java_type,
                                    java_field, is_pk, is_increment, is_required, is_insert, is_edit, is_list, is_query,
                                    query_type, html_type, dict_type, sort, create_by, create_time, update_by,
                                    update_time)
VALUES (26, 2, 'remove_time', '移除时间', 'datetime', 'Date', 'removeTime', '0', '0', '0', '1', '1', '1', '1', 'EQ',
        'datetime', '', 13, 'admin', '2023-12-26 21:56:57', '', '2023-12-26 22:00:11');
INSERT INTO ruoyi.gen_table_column (column_id, table_id, column_name, column_comment, column_type, java_type,
                                    java_field, is_pk, is_increment, is_required, is_insert, is_edit, is_list, is_query,
                                    query_type, html_type, dict_type, sort, create_by, create_time, update_by,
                                    update_time)
VALUES (27, 3, 'id', '主键ID', 'int', 'Long', 'id', '1', '1', '0', '1', null, null, null, 'EQ', 'input', '', 1, 'admin',
        '2024-03-10 15:46:36', '', '2024-03-10 15:57:12');
INSERT INTO ruoyi.gen_table_column (column_id, table_id, column_name, column_comment, column_type, java_type,
                                    java_field, is_pk, is_increment, is_required, is_insert, is_edit, is_list, is_query,
                                    query_type, html_type, dict_type, sort, create_by, create_time, update_by,
                                    update_time)
VALUES (28, 3, 'uuid', '随机UUID', 'varchar(64)', 'String', 'uuid', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input',
        '', 2, 'admin', '2024-03-10 15:46:36', '', '2024-03-10 15:57:12');
INSERT INTO ruoyi.gen_table_column (column_id, table_id, column_name, column_comment, column_type, java_type,
                                    java_field, is_pk, is_increment, is_required, is_insert, is_edit, is_list, is_query,
                                    query_type, html_type, dict_type, sort, create_by, create_time, update_by,
                                    update_time)
VALUES (29, 3, 'name_tag', '服务器名称标签', 'varchar(128)', 'String', 'nameTag', '0', '0', '1', '1', '1', '1', '1',
        'EQ', 'input', '', 3, 'admin', '2024-03-10 15:46:36', '', '2024-03-10 15:57:12');
INSERT INTO ruoyi.gen_table_column (column_id, table_id, column_name, column_comment, column_type, java_type,
                                    java_field, is_pk, is_increment, is_required, is_insert, is_edit, is_list, is_query,
                                    query_type, html_type, dict_type, sort, create_by, create_time, update_by,
                                    update_time)
VALUES (30, 3, 'ip', '服务器IP', 'varchar(128)', 'String', 'ip', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '',
        4, 'admin', '2024-03-10 15:46:36', '', '2024-03-10 15:57:12');
INSERT INTO ruoyi.gen_table_column (column_id, table_id, column_name, column_comment, column_type, java_type,
                                    java_field, is_pk, is_increment, is_required, is_insert, is_edit, is_list, is_query,
                                    query_type, html_type, dict_type, sort, create_by, create_time, update_by,
                                    update_time)
VALUES (31, 3, 'rcon_port', 'RCON远程端口号', 'int', 'Long', 'rconPort', '0', '0', '1', '1', '1', '1', '1', 'EQ',
        'input', '', 5, 'admin', '2024-03-10 15:46:36', '', '2024-03-10 15:57:12');
INSERT INTO ruoyi.gen_table_column (column_id, table_id, column_name, column_comment, column_type, java_type,
                                    java_field, is_pk, is_increment, is_required, is_insert, is_edit, is_list, is_query,
                                    query_type, html_type, dict_type, sort, create_by, create_time, update_by,
                                    update_time)
VALUES (32, 3, 'rcon_password', '远程密码/MD5加密', 'varchar(256)', 'String', 'rconPassword', '0', '0', '1', '1', '1',
        '1', '1', 'EQ', 'input', '', 6, 'admin', '2024-03-10 15:46:36', '', '2024-03-10 15:57:12');
INSERT INTO ruoyi.gen_table_column (column_id, table_id, column_name, column_comment, column_type, java_type,
                                    java_field, is_pk, is_increment, is_required, is_insert, is_edit, is_list, is_query,
                                    query_type, html_type, dict_type, sort, create_by, create_time, update_by,
                                    update_time)
VALUES (33, 3, 'creat_time', '创建时间', 'datetime', 'Date', 'creatTime', '0', '0', '0', '1', '1', '1', '1', 'EQ',
        'datetime', '', 7, 'admin', '2024-03-10 15:46:36', '', '2024-03-10 15:57:12');
INSERT INTO ruoyi.gen_table_column (column_id, table_id, column_name, column_comment, column_type, java_type,
                                    java_field, is_pk, is_increment, is_required, is_insert, is_edit, is_list, is_query,
                                    query_type, html_type, dict_type, sort, create_by, create_time, update_by,
                                    update_time)
VALUES (34, 3, 'creat_by', '创建者', 'varchar(128)', 'String', 'creatBy', '0', '0', '0', '1', '1', '1', '1', 'EQ',
        'input', '', 8, 'admin', '2024-03-10 15:46:36', '', '2024-03-10 15:57:12');
INSERT INTO ruoyi.gen_table_column (column_id, table_id, column_name, column_comment, column_type, java_type,
                                    java_field, is_pk, is_increment, is_required, is_insert, is_edit, is_list, is_query,
                                    query_type, html_type, dict_type, sort, create_by, create_time, update_by,
                                    update_time)
VALUES (35, 3, 'update_time', '更新时间', 'datetime', 'Date', 'updateTime', '0', '0', '0', '1', '1', null, null, 'EQ',
        'datetime', '', 9, 'admin', '2024-03-10 15:46:36', '', '2024-03-10 15:57:12');
INSERT INTO ruoyi.gen_table_column (column_id, table_id, column_name, column_comment, column_type, java_type,
                                    java_field, is_pk, is_increment, is_required, is_insert, is_edit, is_list, is_query,
                                    query_type, html_type, dict_type, sort, create_by, create_time, update_by,
                                    update_time)
VALUES (36, 3, 'update_by', '更新者', 'varchar(128)', 'String', 'updateBy', '0', '0', '0', '1', '1', null, null, 'EQ',
        'input', '', 10, 'admin', '2024-03-10 15:46:36', '', '2024-03-10 15:57:12');
INSERT INTO ruoyi.gen_table_column (column_id, table_id, column_name, column_comment, column_type, java_type,
                                    java_field, is_pk, is_increment, is_required, is_insert, is_edit, is_list, is_query,
                                    query_type, html_type, dict_type, sort, create_by, create_time, update_by,
                                    update_time)
VALUES (37, 3, 'status', '启用状态', 'int', 'Long', 'status', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'radio', '', 11,
        'admin', '2024-03-10 15:46:36', '', '2024-03-10 15:57:12');
INSERT INTO ruoyi.gen_table_column (column_id, table_id, column_name, column_comment, column_type, java_type,
                                    java_field, is_pk, is_increment, is_required, is_insert, is_edit, is_list, is_query,
                                    query_type, html_type, dict_type, sort, create_by, create_time, update_by,
                                    update_time)
VALUES (38, 3, 'remark', '描述', 'text', 'String', 'remark', '0', '0', '0', '1', '1', '1', null, 'EQ', 'textarea', '',
        12, 'admin', '2024-03-10 15:46:36', '', '2024-03-10 15:57:12');
