package com.daiju.pojo.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * course_info
 * @author
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseInfo implements Serializable {
    /**
     * 课程号
     */
    @TableId
    private String cId;

    /**
     * 课程名称
     */
    private String cName;

    /**
     * 课程学时
     */
    private String cPeriod;

    /**
     * 课程学分
     */
    private Double cScore;

    /**
     * 逻辑删除
     */
    @TableField(fill = FieldFill.INSERT)
    private Integer isdel;

    private static final long serialVersionUID = 1L;
}
