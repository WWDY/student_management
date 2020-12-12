package com.daiju.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.daiju.annotation.Author;
import com.daiju.mapper.HomeworkInfoMapper;
import com.daiju.pojo.dto.HomeworkInfo;
import com.daiju.pojo.dto.StuInfo;
import com.daiju.pojo.vo.StuWork;
import com.daiju.service.IStuInfoService;
import com.daiju.service.impl.ChaChoneServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * @Author WDY
 * @Date 2020-12-04 18:26
 * @Description TODO
 */
@Controller
public class StuWorkController {
    @Autowired
    HomeworkInfoMapper homeworkInfoMapper;
    @Autowired
    IStuInfoService stuInfoService;

    @Autowired
    ChaChoneServiceImpl chaChoneService;

    @Value("${spring.uploadPath}")
    private String uploadPath;
    @GetMapping("/getWorkList")
    public String getWorkList(Model model){
        List<HomeworkInfo> homeworkInfos = homeworkInfoMapper.selectList(null);
        List<StuWork> resInfoList = new ArrayList<>();
        List<String> ids = homeworkInfos.stream()
                .map(HomeworkInfo::getStuId)
                .collect(Collectors.toList());
        Map<String, StuInfo> stuInfoMap = new HashMap<>();
        List<StuInfo> stuInfo = stuInfoService.findStuList(new QueryWrapper<StuInfo>().in("s_id", ids));
        stuInfo.forEach(x->{
            stuInfoMap.put(x.getSId(),x);
        });
        homeworkInfos.forEach(x->{
            StuWork stuWork = new StuWork();
            String stuId = x.getStuId();
            StuInfo stuInfo1 = stuInfoMap.get(stuId);
            stuWork.setStuId(stuInfo1.getSId());
            stuWork.setStuName(stuInfo1.getSName());
            stuWork.setStuClass(stuInfo1.getSClass());
            stuWork.setSubmitDate(x.getSubmitDate());
            stuWork.setStuCourseName(x.getCourseName());
            resInfoList.add(stuWork);
        });
        model.addAttribute("workInfo",resInfoList);
        return "work-list";
    }

    @GetMapping("/work/download/{submitDate}")
    public void download(HttpServletResponse response, @PathVariable("submitDate")String submitDate) throws IOException {
        HomeworkInfo submit = homeworkInfoMapper.selectOne(new QueryWrapper<HomeworkInfo>().eq("submit_date", submitDate));
        ServletOutputStream outputStream = response.getOutputStream();
        response.setHeader("Content-Type", "application/octet-stream;charset=utf-8");
        String[] split = StringUtils.split(submit.getFileName(), ".");
        String filename = submit.getStuId()+"-"+submit.getStuName()+"-"+submit.getCourseName()+"."+split[1];
        //告诉浏览器不要进行解析
        response.setHeader("Content-Disposition","attachment;filename="+ URLEncoder.encode(filename,"UTF-8"));
        Files.copy(Paths.get(uploadPath+submit.getFileName()), outputStream);

    }


    @PostMapping("/checkDuplicate")
    @Author()
    public void checkDuplicate(@RequestParam("file")MultipartFile file,HttpServletResponse response) throws Exception {
        AtomicInteger flag = new AtomicInteger(0);
        AtomicReference<String> fileName = new AtomicReference<>();
        chaChoneService.chaChong(file,flag,fileName);
        while (true) {
            if (flag.get() == 1) {
                System.out.println(flag.get());
                ServletOutputStream outputStream = response.getOutputStream();
                response.setHeader("Content-Type", "application/octet-stream;charset=utf-8");
                //告诉浏览器不要进行解析
                response.setHeader("Content-Disposition","attachment;filename="+ URLEncoder.encode(file.getOriginalFilename(),"UTF-8"));
                Files.copy(Paths.get(fileName.get()),outputStream);
                return;
            }
        }
    }
}
