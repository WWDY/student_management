package com.daiju.pojo.dto;

import java.io.Serializable;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


/**
 * course_score
 * @author
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseScore implements Serializable {
    /**
     * 学号
     */
    @JsonProperty("sId")
    @ExcelProperty("学号")
    @TableField("s_id")
    private String stuId;

    /**
     * 姓名
     */
    @ExcelProperty("姓名")
    @TableField("s_name")
    private String stuName;

    /**
     * 课程名称
     */
    @JsonProperty("cName")
    @ExcelIgnore
    private String cName;

    /**
     * 平时成绩
     */
    @ExcelProperty("平时成绩")
    private Double dalyScore;

    /**
     * 期末成绩
     */
    @ExcelProperty("考试成绩")
    private Double testScore;

    /**
     * 总成绩
     */
    @ExcelProperty("总成绩")
    private Double finalScore;

    /**
     * 逻辑删除
     */
    @ExcelIgnore
    @TableField(fill = FieldFill.INSERT)
    private Integer isdel;


    private static final long serialVersionUID = 1L;
}
