package com.daiju.mp;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

/**
 * @Author WDY
 * @Date 2020-11-14 16:46
 * @Description TODO
 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        setFieldValByName("isdel", 0, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
    }
}
