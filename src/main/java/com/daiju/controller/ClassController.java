package com.daiju.controller;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.daiju.annotation.Author;
import com.daiju.mapper.ClassInfoMapper;
import com.daiju.mapper.CourseInfoMapper;
import com.daiju.mapper.CourseScoreMapper;
import com.daiju.mapper.StuInfoMapper;
import com.daiju.pojo.dto.ClassInfo;
import com.daiju.pojo.dto.CourseInfo;
import com.daiju.pojo.dto.CourseScore;
import com.daiju.pojo.dto.StuInfo;
import com.daiju.pojo.vo.CompCourseScore;
import com.daiju.pojo.vo.Courses;
import com.daiju.service.IClassInfoService;
import com.daiju.service.IStuInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author WDY
 * @Date 2020-11-26 17:02
 * @Description TODO
 */
@Controller
public class ClassController {
    @Autowired
    IClassInfoService classInfoService;
    @Autowired
    IStuInfoService stuInfoService;
    @Resource
    StuInfoMapper stuInfoMapper;
    @Resource
    CourseInfoMapper courseInfoMapper;
    @Resource
    CourseScoreMapper courseScoreMapper;
    @Autowired
    ClassInfoMapper classInfoMapper;

    @Author
    @RequestMapping("/class/getClassList")
    public String getClassList(Model model){
        List<ClassInfo> allClassInfo = classInfoService.findAllClassInfo();
        model.addAttribute("classList", allClassInfo);
        return "class-list";
    }
    @Author
    @ResponseBody
    @PostMapping("/class/getClassListJson")
    public Map<String, Object> getClassListJson(){
        List<ClassInfo> allClassInfo = classInfoService.findAllClassInfo();
        Map<String, Object> res = new HashMap<>();
        res.put("data",allClassInfo);
        return res;
    }
    @Author
    @RequestMapping("/class/edit/getOne/{cId}")
    public String getOne(@PathVariable("cId")String cId,Model model){
        //班级信息
        ClassInfo classInfo = classInfoService.findclassInfoById(cId);
        List<StuInfo> stuInfos = stuInfoMapper.selectList(new QueryWrapper<StuInfo>().eq("s_class", classInfo.getCName()));
        //班级人数
        long count = stuInfos.stream().count();
        List<String> stuIds = stuInfos.stream().map(StuInfo::getSId).collect(Collectors.toList());
        List<CourseInfo> courseInfos = courseInfoMapper.selectList(null);
        List<Courses> courses = new ArrayList<>();
        //各科成绩汇总
        List<CompCourseScore> compCourseScores = new ArrayList();
        courseInfos.stream().forEach(infos -> {
            List<CourseScore> courseScores = courseScoreMapper.selectList(new QueryWrapper<CourseScore>().eq("c_name", infos.getCName()).in("s_id", stuIds));
            courses.add(new Courses(courseScores));
        });
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        courses.forEach(x->{
            List<CourseScore> courseScores = x.getCourseScores();
            String cName = courseScores.get(1).getCName();
            String dalySum = decimalFormat.format(courseScores.stream().mapToDouble(CourseScore::getDalyScore).sum());
            String dalyAverage = decimalFormat.format(courseScores.stream().mapToDouble(CourseScore::getDalyScore).average().getAsDouble());
            String testSum = decimalFormat.format(courseScores.stream().mapToDouble(CourseScore::getTestScore).sum());
            String testAverage = decimalFormat.format(courseScores.stream().mapToDouble(CourseScore::getTestScore).average().getAsDouble());
            String finalSum = decimalFormat.format(courseScores.stream().mapToDouble(CourseScore::getFinalScore).sum());
            String finalAverage = decimalFormat.format(courseScores.stream().mapToDouble(CourseScore::getFinalScore).average().getAsDouble());
            compCourseScores.add(new CompCourseScore(cName,Double.valueOf(dalySum), Double.valueOf(testSum), Double.valueOf(finalSum),Double.valueOf(dalyAverage) , Double.valueOf(testAverage), Double.valueOf(finalAverage)));
        });
        System.out.println(courses);

        model.addAttribute("compCourseScores",compCourseScores);
        model.addAttribute("classInfo",classInfo);
        model.addAttribute("stuCount", count);
        return "class-edit";
    }
    @Author
    @GetMapping("/class/exportExcl")
    public void exportExcl(@RequestParam("c_name")String cName, @RequestParam("s_name")String sName, HttpServletResponse response, HttpServletRequest request) throws Exception {
        List<StuInfo> s_class = stuInfoMapper.selectList(new QueryWrapper<StuInfo>().eq("s_class", cName));
        List<String> collect = s_class.stream().map(StuInfo::getSId).collect(Collectors.toList());
        List<CourseScore> courseScores = courseScoreMapper.selectList(new QueryWrapper<CourseScore>().eq("c_name", sName).in("s_id", collect));
        String fileName = cName+sName+"成绩表.xlsx";
        EasyExcel.write(fileName, CourseScore.class).sheet().doWrite(courseScores);
        File file = new File(fileName);
        response.setHeader("Content-Type", "application/octet-stream;charset=utf-8");
        //告诉浏览器不要进行解析
        response.setHeader("Content-Disposition","attachment;filename="+ URLEncoder.encode(fileName,"UTF-8"));
        ServletOutputStream outputStream = response.getOutputStream();
        Files.copy(Paths.get(fileName),outputStream);
        file.delete();
    }
    @Author
    @ResponseBody
    @GetMapping("/echars/getClassAndCourseJson")
    public Map<String,Object> getClassAndCourseJson(){
        List<CourseInfo> courseInfos = courseInfoMapper.selectList(null);
        List<ClassInfo> allClassInfo = classInfoService.findAllClassInfo();
        List<String> courseName = courseInfos.stream().map(CourseInfo::getCName).collect(Collectors.toList());
        Map<String, List<String>> data = new HashMap<>();
        data.put("courseName", courseName);
        Map<String, Object> result = new HashMap<>();
        result.put("data",data);
        result.put("code",200);
        return result;
    }
    @Author
    @ResponseBody
    @GetMapping("/echars/getEchartsInfos")
    public Map<String,Object> getEchartsInfos(@RequestParam("c_id")String cId,@RequestParam("c_name")String cName){
        //每一年级的所有班级
        List<String> allClass = classInfoMapper.selectClassNameByStartCId(cId);
        //每个班级的所有学号
        List<List<String>> sId = allClass.stream().map(x -> {
            List<StuInfo> s_class = stuInfoService.findStuList(new QueryWrapper<StuInfo>().eq("s_class", x));
            return s_class.stream().map(StuInfo::getSId).collect(Collectors.toList());
        }).collect(Collectors.toList());
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        //所属科目每个班级的各项成绩平均分
        List<Map<String, String>> resScore = sId.stream().map(x -> {
            int count = x.size();
            List<CourseScore> courseScores = courseScoreMapper.selectList(new QueryWrapper<CourseScore>().eq("c_name", cName).in("s_id", x));
            String delyScore = decimalFormat.format(courseScores.stream().mapToDouble(CourseScore::getDalyScore).average().getAsDouble());
            String testScore = decimalFormat.format(courseScores.stream().mapToDouble(CourseScore::getTestScore).average().getAsDouble());
            String finalScore = decimalFormat.format(courseScores.stream().mapToDouble(CourseScore::getFinalScore).average().getAsDouble());
            Map<String, String> score = new HashMap<>();
            score.put("delyScore", delyScore);
            score.put("testScore", testScore);
            score.put("finalScore", finalScore);
            return score;
        }).collect(Collectors.toList());
        Map<String,Object> result = new HashMap<>();
        result.put("allClass",allClass);
        result.put("resScore", resScore);
        result.put("code", 200);
        return result;
    }
}
