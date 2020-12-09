create table class_info
(
    c_id   varchar(20)   not null comment '班级编号',
    c_name varchar(20)   null comment '行政班级',
    isdel  int default 0 not null comment '逻辑删除字段',
    constraint class_info_c_id_uindex
        unique (c_id)
)
    comment '班级信息';

alter table class_info
    add primary key (c_id);

INSERT INTO stu_management.class_info (c_id, c_name, isdel) VALUES ('1601', '16Java1班', 0);
INSERT INTO stu_management.class_info (c_id, c_name, isdel) VALUES ('1602', '16Java2班', 0);
INSERT INTO stu_management.class_info (c_id, c_name, isdel) VALUES ('1603', '16Java3班', 0);
INSERT INTO stu_management.class_info (c_id, c_name, isdel) VALUES ('1604', '16大数据1班', 0);
INSERT INTO stu_management.class_info (c_id, c_name, isdel) VALUES ('1605', '16大数据2班', 0);
INSERT INTO stu_management.class_info (c_id, c_name, isdel) VALUES ('1606', '16.Net1班', 0);
INSERT INTO stu_management.class_info (c_id, c_name, isdel) VALUES ('1607', '16.Net2班', 0);
INSERT INTO stu_management.class_info (c_id, c_name, isdel) VALUES ('1701', '17Java1班', 0);
INSERT INTO stu_management.class_info (c_id, c_name, isdel) VALUES ('1702', '17Java2班', 0);
INSERT INTO stu_management.class_info (c_id, c_name, isdel) VALUES ('1703', '17Java3班', 0);
INSERT INTO stu_management.class_info (c_id, c_name, isdel) VALUES ('1704', '17大数据1班', 0);
INSERT INTO stu_management.class_info (c_id, c_name, isdel) VALUES ('1705', '17大数据2班', 0);
INSERT INTO stu_management.class_info (c_id, c_name, isdel) VALUES ('1706', '17.Net1班', 0);
INSERT INTO stu_management.class_info (c_id, c_name, isdel) VALUES ('1707', '17.Net2班', 0);
INSERT INTO stu_management.class_info (c_id, c_name, isdel) VALUES ('1801', '18Java1班', 0);
INSERT INTO stu_management.class_info (c_id, c_name, isdel) VALUES ('1802', '18Java2班', 0);
INSERT INTO stu_management.class_info (c_id, c_name, isdel) VALUES ('1803', '18Java3班', 0);
INSERT INTO stu_management.class_info (c_id, c_name, isdel) VALUES ('1804', '18大数据1班', 0);
INSERT INTO stu_management.class_info (c_id, c_name, isdel) VALUES ('1805', '18大数据2班', 0);
INSERT INTO stu_management.class_info (c_id, c_name, isdel) VALUES ('1806', '18.Net1班', 0);
INSERT INTO stu_management.class_info (c_id, c_name, isdel) VALUES ('1807', '18.Net2班', 0);
INSERT INTO stu_management.class_info (c_id, c_name, isdel) VALUES ('1901', '19Java1班', 0);
INSERT INTO stu_management.class_info (c_id, c_name, isdel) VALUES ('1902', '19Java2班', 0);
INSERT INTO stu_management.class_info (c_id, c_name, isdel) VALUES ('1903', '19Java3班', 0);
INSERT INTO stu_management.class_info (c_id, c_name, isdel) VALUES ('1904', '19大数据1班', 0);
INSERT INTO stu_management.class_info (c_id, c_name, isdel) VALUES ('1905', '19大数据2班', 0);
INSERT INTO stu_management.class_info (c_id, c_name, isdel) VALUES ('1906', '19.Net1班', 0);
INSERT INTO stu_management.class_info (c_id, c_name, isdel) VALUES ('1907', '19.Net2班', 0);
INSERT INTO stu_management.class_info (c_id, c_name, isdel) VALUES ('2001', '20Java1班', 0);
INSERT INTO stu_management.class_info (c_id, c_name, isdel) VALUES ('2002', '20Java2班', 0);
INSERT INTO stu_management.class_info (c_id, c_name, isdel) VALUES ('2003', '20Java3班', 0);
INSERT INTO stu_management.class_info (c_id, c_name, isdel) VALUES ('2004', '20大数据1班', 0);
INSERT INTO stu_management.class_info (c_id, c_name, isdel) VALUES ('2005', '20大数据2班', 0);
INSERT INTO stu_management.class_info (c_id, c_name, isdel) VALUES ('2006', '20.Net1班', 0);
INSERT INTO stu_management.class_info (c_id, c_name, isdel) VALUES ('2007', '20.Net2班', 0);