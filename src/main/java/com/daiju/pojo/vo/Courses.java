package com.daiju.pojo.vo;

import com.daiju.pojo.dto.CourseScore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author WDY
 * @Date 2020-11-27 17:44
 * @Description TODO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Courses {
    private List<CourseScore> courseScores;
}
