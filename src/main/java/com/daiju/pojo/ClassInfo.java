package pojo;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * class_info
 * @author
 */
@Data
public class ClassInfo implements Serializable {
    /**
     * 班级编号
     */
    @TableId
    private String cId;

    /**
     * 行政班级
     */
    private String cName;

    /**
     * 逻辑删除字段
     */
    @TableField(fill = FieldFill.INSERT)
    private Integer isdel;

    private static final long serialVersionUID = 1L;
}
