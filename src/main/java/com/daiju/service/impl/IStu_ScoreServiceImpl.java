package com.daiju.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.daiju.mapper.CourseScoreMapper;
import com.daiju.mapper.StuInfoMapper;
import com.daiju.pojo.dto.CourseScore;
import com.daiju.pojo.dto.StuInfo;
import com.daiju.pojo.vo.Stu_Score;
import com.daiju.service.IStu_ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author WDY
 * @Date 2020-11-22 17:39
 * @Description TODO
 */
@Service
public class IStu_ScoreServiceImpl implements IStu_ScoreService {
    @Autowired
    private StuInfoMapper stuInfoMapper;
    @Autowired
    private CourseScoreMapper courseScoreMapper;
    @Override
    @Cacheable(cacheNames = "stu_score",key = "#id")
    public Stu_Score findById(String id) {
        StuInfo stuInfo = stuInfoMapper.selectById(id);
        List<CourseScore> courseScore = courseScoreMapper.selectList(new QueryWrapper<CourseScore>().eq("s_id",id));
        return new Stu_Score(stuInfo,courseScore);
    }

    @Override
    @CacheEvict(cacheNames = "stu_score",key = "#stuInfo.SId")
    public Integer updateStuInfoByid(StuInfo stuInfo) {
        return stuInfoMapper.updateById(stuInfo);
    }

    @Override
    @CacheEvict(cacheNames = "stu_score",key = "#courseScore.stuId")
    public Integer updateScoreInfo(CourseScore courseScore) {
        return courseScoreMapper.update(courseScore, new QueryWrapper<CourseScore>().eq("s_id", courseScore.getStuId()).eq("c_name", courseScore.getCName()));
    }
}
