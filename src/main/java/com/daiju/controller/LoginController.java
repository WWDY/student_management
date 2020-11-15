package com.daiju.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.daiju.mapper.LoginInfoMapper;
import com.daiju.pojo.LoginInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @Author WDY
 * @Date 2020-11-14 20:28
 * @Description TODO
 */
@Controller
public class LoginController {
    @Resource
    LoginInfoMapper loginInfoMapper;

    @PostMapping("/user/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model,HttpSession session) {
        LoginInfo loginInfo = loginInfoMapper.selectById(username);
        if(loginInfo==null){
            model.addAttribute("msg","账号不存在");
            return "index";
        }else {
            if(loginInfo.getPassword().equals(password)){
                session.setAttribute("user",loginInfo);
                return "redirect:/manage";
            }else {
                model.addAttribute("msg","账号或密码错误");
                return "index";
            }
        }


    }



}
