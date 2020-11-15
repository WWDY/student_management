package com.daiju.mp.base;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @Author WDY
 * @Date 2020-11-14 15:20
 * @Description TODO
 */
public interface MyBase<T> extends BaseMapper<T> {
    int insertBatchSomeColumn(List<T> entityList);
}
