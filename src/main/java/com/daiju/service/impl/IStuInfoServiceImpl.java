package com.daiju.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.daiju.mapper.StuInfoMapper;
import com.daiju.pojo.dto.StuInfo;
import com.daiju.service.IStuInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author WDY
 * @Date 2020-11-18 23:12
 * @Description TODO
 */
@Service
public class IStuInfoServiceImpl implements IStuInfoService {
    @Autowired
    StuInfoMapper stuInfoMapper;
    @Override
    public void addStu(StuInfo stuInfo) {
        stuInfoMapper.insert(stuInfo);
    }

    @Override
    @CacheEvict(cacheNames = {"stuInfo"},key = "#sId")
    public void delStuBySId(String sId) {
        stuInfoMapper.deleteById(sId);
    }

    @Override
    @CachePut(cacheNames = {"stuInfo"},key = "#stuInfo.SId")
    public void updateStu(StuInfo stuInfo) {
        stuInfoMapper.updateById(stuInfo);
    }

    @Override
    @Cacheable(cacheNames = {"stuInfo"},key = "#sId")
    public StuInfo findStuBySId(String sId) {
        return stuInfoMapper.selectById(sId);
    }

    @Override
    public List<StuInfo> findStuList(QueryWrapper<StuInfo> queryWrapper) {
        return stuInfoMapper.selectList(queryWrapper);
    }
}
