package com.daiju.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @Author WDY
 * @Date 2020-11-27 18:46
 * @Description TODO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompCourseScore {
    private String courseName;
    private Double finalDalyScore;
    private Double finalTestScore;
    private Double finalAllScore;
    private Double avgDalyScore;
    private Double avgTestScore;
    private Double avgAllScore;
}
