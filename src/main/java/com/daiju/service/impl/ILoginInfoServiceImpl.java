package com.daiju.service.impl;

import com.daiju.mapper.LoginInfoMapper;
import com.daiju.pojo.dto.LoginInfo;
import com.daiju.service.ILoginInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @Author WDY
 * @Date 2020-11-18 22:40
 * @Description TODO
 */
@Service
public class ILoginInfoServiceImpl implements ILoginInfoService {

    @Autowired
    LoginInfoMapper loginInfoMapper;
    @Override
    @Cacheable(cacheNames = "loginInfo",key = "#username")
    public LoginInfo findByUsername(String username) {
        return loginInfoMapper.selectById(username);
    }

    @Override
    @CachePut(cacheNames = "loginInfo",key = "#loginInfo.username")
    public void updateUsername(LoginInfo loginInfo) {
        loginInfoMapper.updateById(loginInfo);
    }

    @Override
    @CacheEvict(cacheNames = "loginInfo",key = "#username")
    public void delByUsername(String username) {
        loginInfoMapper.deleteById(username);
    }

    @Override
    public void addLoginInfo(LoginInfo loginInfo) {
        loginInfoMapper.insert(loginInfo);
    }
}
