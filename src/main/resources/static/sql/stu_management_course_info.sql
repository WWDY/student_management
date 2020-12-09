create table course_info
(
    c_id     varchar(20)   not null comment '课程号',
    c_name   varchar(30)   null comment '课程名称',
    c_period varchar(10)   null comment '课程学时',
    c_score  double        null comment '课程学分',
    isdel    int default 0 not null comment '逻辑删除',
    constraint c_id
        unique (c_id)
)
    comment '课程信息表';

alter table course_info
    add primary key (c_id);

INSERT INTO stu_management.course_info (c_id, c_name, c_period, c_score, isdel) VALUES ('1001', '毛中特', '54', 3, 0);
INSERT INTO stu_management.course_info (c_id, c_name, c_period, c_score, isdel) VALUES ('1002', '计算机网络', '64', 4, 0);
INSERT INTO stu_management.course_info (c_id, c_name, c_period, c_score, isdel) VALUES ('1003', 'Java EE视图层框架技术', '32', 2, 0);
INSERT INTO stu_management.course_info (c_id, c_name, c_period, c_score, isdel) VALUES ('1004', 'Oracle数据库', '32', 2, 0);
INSERT INTO stu_management.course_info (c_id, c_name, c_period, c_score, isdel) VALUES ('1005', '软件前沿技术', '32', 2, 0);
INSERT INTO stu_management.course_info (c_id, c_name, c_period, c_score, isdel) VALUES ('1006', '软件工程导论', '32', 2, 0);
INSERT INTO stu_management.course_info (c_id, c_name, c_period, c_score, isdel) VALUES ('1007', 'Java EE持久层框架技术', '32', 2, 0);
INSERT INTO stu_management.course_info (c_id, c_name, c_period, c_score, isdel) VALUES ('1008', '媒体设计方法学', '32', 2, 0);
INSERT INTO stu_management.course_info (c_id, c_name, c_period, c_score, isdel) VALUES ('1009', '企业项目实战', '32', 2, 0);