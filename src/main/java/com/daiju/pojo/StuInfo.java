package com.daiju.pojo;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * stu_info
 * @author
 */
@Data
public class StuInfo implements Serializable {
    /**
     * 学号
     */
    @TableId
    private String sId;

    /**
     * 姓名
     */
    private String sName;

    /**
     * 性别
     */
    private String sSex;

    /**
     * 所在院校
     */
    private String sColleges;

    /**
     * 行政班级
     */
    private String sClass;

    /**
     * 手机号
     */
    private String sTel;

    /**
     * 逻辑删除
     */
    @TableField(fill = FieldFill.INSERT)
    private Integer isdel;

    private static final long serialVersionUID = 1L;
}
