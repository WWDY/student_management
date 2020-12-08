package com.daiju.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

/**
 * @Author WDY
 * @Date 2020-12-04 18:35
 * @Description TODO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StuWork implements Serializable {
    private static final Long serialVersionUID = 1L;

    /**
     * 学号
     */
    private String stuId;

    /**
     * 姓名
     */
    private String stuName;


    /**
     * 行政班级
     */
    private String stuClass;

    /**
     * 学科名称
     */
    private String stuCourseName;

    /**
     * 提交日期
     */
    private String submitDate;



    @Override
    public int hashCode() {
        return Objects.hash(stuId);
    }
}
