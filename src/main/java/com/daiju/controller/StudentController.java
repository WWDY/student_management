package com.daiju.controller;

import com.daiju.mapper.CourseInfoMapper;
import com.daiju.mapper.HomeworkInfoMapper;
import com.daiju.pojo.dto.CourseInfo;
import com.daiju.pojo.dto.HomeworkInfo;
import com.daiju.pojo.dto.LoginInfo;
import com.daiju.pojo.dto.StuInfo;
import com.daiju.service.IStuInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author WDY
 * @Date 2020-11-30 20:51
 * @Description TODO
 */
@RestController
public class StudentController {
    @Autowired
    IStuInfoService stuInfoService;
    @Autowired
    CourseInfoMapper courseInfoMapper;
    @Autowired
    HomeworkInfoMapper homeworkInfoMapper;

    @Value("${spring.uploadPath}")
    private String uploadPath;
    @GetMapping("/stu/getBaseInfo")
    public Map<String,Object> getBaseInfo(HttpSession session){
        LoginInfo user = (LoginInfo)session.getAttribute("user");
        StuInfo stu = stuInfoService.findStuBySId(user.getUsername());
        List<CourseInfo> courseInfos = courseInfoMapper.selectList(null);
        Map<String, Object> result = new HashMap<>();
        result.put("stuInfo",stu);
        result.put("code", 200);
        result.put("courseInfo", courseInfos);
        return result;
    }

    @PostMapping(value = "/stu/task/submit")
    public Map<String, Object> uploadFile(@RequestParam("file") MultipartFile file,
                             @RequestParam("sId")String sId,
                             @RequestParam("sName")String sName,
                             @RequestParam("courseName")String courseName) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String[] split = StringUtils.split(originalFilename, ".");
        long timeMillis = System.currentTimeMillis();
        String filename = sId+"-"+sName+"-"+courseName+"-"+timeMillis+"."+split[1];
        file.transferTo(new File(uploadPath,filename));
        Map<String, Object> res  = new HashMap<>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        HomeworkInfo homeworkInfo = new HomeworkInfo();
        homeworkInfo.setStuId(sId);
        homeworkInfo.setStuName(sName);
        homeworkInfo.setCourseName(courseName);
        homeworkInfo.setFileName(filename);
        homeworkInfo.setSubmitDate(format.format(new Date(timeMillis)));
        homeworkInfoMapper.insert(homeworkInfo);
        res.put("code",200);
        return res;
    }

}
