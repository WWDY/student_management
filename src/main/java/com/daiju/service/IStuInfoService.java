package com.daiju.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.daiju.pojo.dto.StuInfo;

import java.util.List;

/**
 * @Author WDY
 * @Date 2020-11-18 23:10
 * @Description TODO
 */
public interface IStuInfoService {

    void addStu(StuInfo stuInfo);

    void delStuBySId(String sId);

    void updateStu(StuInfo stuInfo);

    StuInfo findStuBySId(String sId);

    List<StuInfo> findStuList(QueryWrapper<StuInfo> queryWrapper);
}
