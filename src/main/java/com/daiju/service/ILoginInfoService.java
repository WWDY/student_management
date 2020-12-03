package com.daiju.service;

import com.daiju.pojo.dto.LoginInfo;

/**
 * @Author WDY
 * @Date 2020-11-18 22:36
 * @Description TODO
 */
public interface ILoginInfoService {
    LoginInfo findByUsername(String username);
    void updateUsername(LoginInfo loginInfo);
    void delByUsername(String username);
    void addLoginInfo(LoginInfo loginInfo);
}
