package com.daiju.mapper;

import com.daiju.mp.base.MyBase;
import com.daiju.pojo.dto.ClassInfo;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author WDY
 * @Date 2020-11-14 16:59
 * @Description TODO
 */
@Repository
public interface ClassInfoMapper extends MyBase<ClassInfo> {
    @Select("SELECT c_name from class_info where left(c_id,2)=#{cId}")
    List<String> selectClassNameByStartCId(String cId);
}
