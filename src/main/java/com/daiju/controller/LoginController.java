package com.daiju.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.daiju.mapper.LoginInfoMapper;
import com.daiju.mapper.StuInfoMapper;
import com.daiju.pojo.LoginInfo;
import com.daiju.pojo.StuInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author WDY
 * @Date 2020-11-14 20:28
 * @Description TODO
 */
@Controller
public class LoginController {
    @Resource
    LoginInfoMapper loginInfoMapper;

    @Autowired
    StuInfoMapper stuInfoMapper;


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
                return "redirect:/dashboard";
            }else {
                model.addAttribute("msg","账号或密码错误");
                return "index";
            }
        }


    }

    @GetMapping("/user/logout")
    public String logout(HttpSession session){
        session.removeAttribute("msg");
        return "redirect:/index";
    }


    @GetMapping("/user/getUsers")
    public String getUsers(Model model,@RequestParam(value = "currentPage",required = false) String currentPage) {
        Page<StuInfo> stuInfoPage = new Page<>(currentPage==null?1L:Long.valueOf(currentPage), 20);
        Page<StuInfo> stuInfos = stuInfoMapper.selectPage(stuInfoPage,null);
        List<StuInfo> records = stuInfos.getRecords();
        long current = stuInfos.getCurrent();
        long total = stuInfos.getTotal();
        long pageSize;
        if(total % 20 != 0){
            pageSize = total / 20 +1;
        }else {
            pageSize = total / 20;
        }
        model.addAttribute("users", records);
        model.addAttribute("cuuentPage", currentPage);
        model.addAttribute("pages",pageSize);
        return "user-list";
    }



}
