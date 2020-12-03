package com.daiju.service;

import com.daiju.pojo.dto.ClassInfo;

import java.util.List;

/**
 * @Author WDY
 * @Date 2020-11-26 16:34
 * @Description TODO
 */
public interface IClassInfoService {
    void addClass(ClassInfo classInfo);

    void deleteClassById(String id);

    void updateClass(ClassInfo classInfo);

    ClassInfo findclassInfoById(String id);

    List<ClassInfo> findAllClassInfo();
}
