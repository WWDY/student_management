package com.daiju.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author WDY
 * @Date 2020-12-01 9:24
 * @Description TODO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HomeworkInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String stuName;
    private String stuId;
    private String submitDate;
    private String courseName;
    private String fileName;
}
