package com.daiju.pojo;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * course_score
 * @author
 */
@Data
public class CourseScore implements Serializable {
    /**
     * 学号
     */
    private String sId;

    /**
     * 姓名
     */
    private String sName;

    /**
     * 课程名称
     */
    private String cName;

    /**
     * 平时成绩
     */
    private Double dalyScore;

    /**
     * 期末成绩
     */
    private Double testScore;

    /**
     * 总成绩
     */
    private Double finalScore;

    /**
     * 逻辑删除
     */
    @TableField(fill = FieldFill.INSERT)
    private Integer isdel;

    private static final long serialVersionUID = 1L;
}
