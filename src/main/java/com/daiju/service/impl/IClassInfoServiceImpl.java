package com.daiju.service.impl;

import com.daiju.mapper.ClassInfoMapper;
import com.daiju.pojo.dto.ClassInfo;
import com.daiju.service.IClassInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author WDY
 * @Date 2020-11-26 16:36
 * @Description TODO
 */
@Repository
public class IClassInfoServiceImpl implements IClassInfoService {
    @Autowired
    ClassInfoMapper classInfoMapper;
    @Override
    public void addClass(ClassInfo classInfo) {
        classInfoMapper.insert(classInfo);
    }

    @Override
    @CacheEvict(cacheNames = {"classInfo"},key = "#id")
    public void deleteClassById(String id) {
        classInfoMapper.deleteById(id);
    }

    @Override
    @CachePut(cacheNames = {"classInfo"},key = "#classInfo.cId")
    public void updateClass(ClassInfo classInfo) {
         classInfoMapper.updateById(classInfo);
    }

    @Override
    @Cacheable(cacheNames = {"classInfo"},key = "#id")
    public ClassInfo findclassInfoById(String id) {
        return classInfoMapper.selectById(id);
    }

    @Override
    public List<ClassInfo> findAllClassInfo() {
        return classInfoMapper.selectList(null);
    }
}
