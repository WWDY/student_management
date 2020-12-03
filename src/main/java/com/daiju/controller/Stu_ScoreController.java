package com.daiju.controller;

import com.daiju.annotation.Author;
import com.daiju.mapper.ClassInfoMapper;
import com.daiju.pojo.dto.ClassInfo;
import com.daiju.pojo.dto.CourseScore;
import com.daiju.pojo.dto.StuInfo;
import com.daiju.pojo.vo.Stu_Score;
import com.daiju.service.IStuInfoService;
import com.daiju.service.IStu_ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author WDY
 * @Date 2020-11-22 18:02
 * @Description TODO
 */
@Controller
public class Stu_ScoreController {
    @Autowired
    IStu_ScoreService stu_scoreService;
    @Autowired
    ClassInfoMapper classInfoMapper;
    @Autowired
    IStuInfoService stuInfoService;
    @Author
    @RequestMapping("/user/edit/getOne/{id}")
    public String getOneInfo(Model model, @PathVariable("id") String sId){
        Stu_Score stu_score = stu_scoreService.findById(sId);
        List<ClassInfo> classInfos = classInfoMapper.selectList(null);
        List<String> classNames = classInfos.stream().map(ClassInfo::getCName).collect(Collectors.toList());
        model.addAttribute("stu_score",stu_score);
        model.addAttribute("classNames", classNames);
        return "user-edit";
    }
    @Author
    @PutMapping("/user/edit/score/update")
    @ResponseBody
    public String updateScore(@RequestBody CourseScore courseScore){
        if(courseScore==null){
            return "error";
        }
        Integer integer = stu_scoreService.updateScoreInfo(courseScore);
        if (integer == 1) {
            return "success";
        }else {
            return "error";
        }

    }
    @Author
    @PutMapping("/user/edit/stu/update")
    @ResponseBody
    public String updateStu(@RequestBody StuInfo stuInfo){
        if (stuInfo==null) {
            return "error";
        }else {
            stuInfoService.updateStu(stuInfo);
            return "success";
        }
    }
}
