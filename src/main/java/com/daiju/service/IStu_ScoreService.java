package com.daiju.service;

import com.daiju.pojo.dto.CourseScore;
import com.daiju.pojo.dto.StuInfo;
import com.daiju.pojo.vo.Stu_Score;

/**
 * @Author WDY
 * @Date 2020-11-22 17:39
 * @Description TODO
 */
public interface IStu_ScoreService {
    /**
     * 查找学生信息和成绩
     * @param id 学号
     * @return
     */
    Stu_Score findById(String id);

    /**
     * 更新学生基本信息
     * @param stuInfo 学生信息
     */
    Integer updateStuInfoByid(StuInfo stuInfo);

    /**
     *  更新成绩
     * @param courseScore 课程信息
     */
    Integer updateScoreInfo(CourseScore courseScore);

}
