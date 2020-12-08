package com.daiju.pojo.vo;

import com.daiju.pojo.dto.CourseScore;
import com.daiju.pojo.dto.StuInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @Author WDY
 * @Date 2020-11-22 17:37
 * @Description TODO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stu_Score implements Serializable {
    private static final Long serialVersionUID = 1L;
    private StuInfo stuInfo;
    private List<CourseScore> courseScores;
}
