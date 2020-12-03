package com.daiju.pojo.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

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
    @JsonProperty("sId")
    private String sId;

    /**
     * 姓名
     */
    @JsonProperty("sName")
    private String sName;

    /**
     * 性别
     */
    @JsonProperty("sSex")
    private String sSex;

    /**
     * 所在院校
     */
    @JsonProperty("sColleges")
    private String sColleges;

    /**
     * 行政班级
     */
    @JsonProperty("sClass")
    private String sClass;

    /**
     * 手机号
     */
    @JsonProperty("sTel")
    private String sTel;

    /**
     * 逻辑删除
     */
    @TableField(fill = FieldFill.INSERT)
    private Integer isdel;

    private static final long serialVersionUID = 1L;
}
