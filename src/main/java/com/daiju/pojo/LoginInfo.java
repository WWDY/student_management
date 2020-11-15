package com.daiju.pojo;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * login_info
 * @author
 */
@Data
public class LoginInfo implements Serializable {
    /**
     * 用户名
     */
    @TableId
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 逻辑删除字段
     */
    @TableField(fill = FieldFill.INSERT)
    private Integer isdel;

    private static final long serialVersionUID = 1L;
}
