package com.daiju.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.daiju.annotation.Author;
import com.daiju.mapper.StuInfoMapper;
import com.daiju.pojo.dto.ClassInfo;
import com.daiju.pojo.dto.LoginInfo;
import com.daiju.pojo.dto.StuInfo;
import com.daiju.service.IClassInfoService;
import com.daiju.service.impl.ILoginInfoServiceImpl;
import com.daiju.service.impl.IStuInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    ILoginInfoServiceImpl loginInfoService;

    @Resource
    IStuInfoServiceImpl stuInfoService;

    @Autowired
    StuInfoMapper stuInfoMapper;

    @Autowired
    IClassInfoService classInfoService;

    @PostMapping("/user/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model,HttpSession session) {
        LoginInfo loginInfo = loginInfoService.findByUsername(username);
        if(loginInfo==null){
            model.addAttribute("msg","账号不存在");
            return "index";
        }else {
            if(loginInfo.getPassword().equals(password)){
                session.setAttribute("user",loginInfo);
                if(username.length()>4){
                    return "redirect:/stu/task";
                }
                if (session.getAttribute("redirect")!=null&&!session.getAttribute("redirect").equals("/favicon.ico")) {
                    return "redirect:"+session.getAttribute("redirect");
                }else {
                    return "redirect:/dashboard";
                }
            }else {
                model.addAttribute("msg","账号或密码错误");
                return "index";
            }
        }


    }

    @GetMapping("/user/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/index";
    }

    @Author
    @DeleteMapping("/user/delUser/{id}")
    @ResponseBody
    public String delStu(@PathVariable("id") String id){
        stuInfoService.delStuBySId(id);
        return "success";
    }


    @Author
    @GetMapping("/user/getUsers")
    public String getStus(Model model,
                          @RequestParam(value = "currentPage",required = false) String currentPage,
                          @RequestParam(value = "cName",required = false) String cName) {
        if(currentPage==null){
            currentPage = "1";
        }
        List<ClassInfo> allClassInfo = classInfoService.findAllClassInfo();
        Page<StuInfo> stuInfoPage = new Page<>(Long.valueOf(currentPage), 20);
        Page<StuInfo> stuInfos = null;
        if(cName==null||cName.equals("0")){
            stuInfos = stuInfoMapper.selectPage(stuInfoPage,null);
            model.addAttribute("url", "/user/getUsers?cName=0&currentPage=");
        }else {
            QueryWrapper<StuInfo> stuInfoQueryWrapper = new QueryWrapper<>();
            stuInfoQueryWrapper.eq("s_class",cName);
            stuInfos = stuInfoMapper.selectPage(stuInfoPage,stuInfoQueryWrapper);
            model.addAttribute("url", "/user/getUsers?cName="+cName+"&currentPage=");
        }
        List<StuInfo> records = stuInfos.getRecords();
        long current = stuInfos.getCurrent();
        long total = stuInfos.getTotal();
        long pageSize;
        if(total % 20 != 0){
            pageSize = total / 20 +1;
        }else {
            pageSize = total / 20;
        }
        model.addAttribute("classList", allClassInfo);
        model.addAttribute("users", records);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("pages",pageSize);
        return "user-list";
    }





}
