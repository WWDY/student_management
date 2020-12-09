create table homework_info
(
    stu_id      int          null,
    stu_name    varchar(255) null,
    submit_date varchar(255) null,
    file_name   varchar(255) null,
    course_name varchar(255) null
)
    comment '作业管理表';

create index homework_info_stu_id_index
    on homework_info (stu_id);

INSERT INTO stu_management.homework_info (stu_id, stu_name, submit_date, file_name, course_name) VALUES (1601001, '鲁星禧', '2020-12-04 20:30:03', '1601001-鲁星禧-毛中特-1607085003247.docx', '毛中特');
INSERT INTO stu_management.homework_info (stu_id, stu_name, submit_date, file_name, course_name) VALUES (1601001, '鲁星禧', '2020-12-08 15:41:57', '1601001-鲁星禧-毛中特-1607413317385.docx', '毛中特');