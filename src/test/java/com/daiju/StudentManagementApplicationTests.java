package com.daiju;

import com.daiju.mapper.CourseInfoMapper;
import com.daiju.mapper.CourseScoreMapper;
import com.daiju.mapper.StuInfoMapper;
import com.daiju.pojo.CourseInfo;
import com.daiju.pojo.CourseScore;
import com.daiju.pojo.StuInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@SpringBootTest
class StudentManagementApplicationTests {


    @Autowired
    StuInfoMapper stuInfoMapper;

    @Autowired
    CourseInfoMapper courseInfoMapper;

    @Autowired
    CourseScoreMapper courseScoreMapper;

    @Test
    void contextLoads() {
        String firstName = "赵 钱 孙 李 周 吴 郑 王 冯 陈 褚 卫 蒋 沈 韩 杨 朱 秦 尤 许 何 吕 施 张 孔 曹 严 华 金 魏 陶 姜 戚 谢 邹 喻 柏 水 窦 章 云 苏 潘 葛 奚 范 彭 郎 鲁 韦 昌 马 苗 凤 花 方 俞 任 袁 柳 酆 鲍 史 唐 费 廉 岑 薛 雷 贺 倪 汤 滕 殷 罗 毕 郝 邬 安 常 乐 于 时 傅 皮 卞 齐 康 伍 余 元 卜 顾 孟 平 黄 和 穆 萧 尹 姚 邵 湛 汪 祁 毛 禹 狄 米 贝 明 臧 计 伏 成 戴 谈 宋 茅 庞 熊 纪 舒 屈 项 祝 董 梁 杜 阮 蓝 闵 席 季 麻 强 贾 路 娄 危 江 童 颜 郭 梅 盛 林 刁 钟 徐 邱 骆 高 夏 蔡 田 樊 胡 凌 霍 虞 万 支 柯 昝 管 卢 莫 经 房 裘 缪 干 解 应 宗 丁 宣 贲 邓 郁 单 杭 洪 包 诸 左 石 崔 吉 钮 龚 程 嵇 邢 滑 裴 陆 荣 翁 荀 羊 於 惠 甄 曲 家 封 芮 羿 储 靳 汲 邴 糜 松 井 段 富 巫 乌 焦 巴 弓 牧 隗 山 谷 车 侯 宓 蓬 全 郗 班 仰 秋 仲 伊 宫 宁 仇 栾 暴 甘 钭 厉 戎 祖 武 符 刘 景 詹 束 龙 叶 幸 司 韶 郜 黎 蓟 薄 印 宿 白 怀 蒲 台 从 鄂 索 咸 籍 赖 卓 蔺 屠 蒙 池 乔 阴 欎 胥 能 苍 双 闻 莘 党 翟 谭 贡 劳 逄 姬 申 扶 堵 冉 宰 郦 雍 郤 璩 桑 桂 濮 牛 寿 通 边 扈 燕 冀 郏 浦 尚 农 温 别 庄 晏 柴 瞿 阎 充 慕 连 茹 习 宦 艾 鱼 容 向 古 易 慎 戈 廖 庾 终 暨 居 衡 步 都 耿 满 弘 匡 国 文 寇 广 禄 阙 东 殴 殳 沃 利 蔚 越 夔 隆 师 巩 厍 聂 晁 勾 敖 融 冷 訾 辛 阚 那 简 饶 空 曾 毋 沙 乜 养 鞠 须 丰 巢 关 蒯 相 查 后 荆 红";
        String lastName = "梁鸿 诚桓 权泽 福年 家荣 锐骏 仕禄 颜日 俊震 杰中 盛年 良锟 礼振 强卓 家逸 贤欣 暄斌 阳良 盛浩 帆峰 翰哲 远鸿 诚初 祥恒 强锋 康涛 年琛 鹏盛 子锋 谛畅 钊禧 材贤 弘日 潍逸 琛炳 勇槐 祜郁 祜礼 俊康 铭延 荣槐 子林 铭澄 禄伟 乘运 栋蔓 裕俊 骞礼 德帆 加琛 辰嘉 凯侠 彬祜 锋泽 锋濡 喆鹏 桓卓 安吉 权运 远澄 卓钊 吉德 材鹏 腾允 勇权 奇林 强浩 尧芃 逸福 梁侠 澄盛 星禧 运日 贤振 鑫德 尧恒 烁嘉 卓澄 伟喆 翱年";
        String[] sclass = {"Java1班","Java2班","Java3班","大数据1班","大数据2班",".Net1班",".Net2班"};
        String[] sex = {"男","女"};
        String[] firstNames = firstName.split(" ");
        String[] lastNames = lastName.split(" ");
        Random random = new Random();
        int x = 1001;
        for (int i = 0; i < sclass.length; i++) {
            List<StuInfo> stuInfos = new ArrayList<>();
            for (int j = 0; j < (random.nextInt(16)+40); j++) {
                StuInfo stuInfo = new StuInfo();
                int i1 = random.nextInt(firstNames.length);
                int i2 = random.nextInt(lastNames.length);
                stuInfo.setSId("200"+x++);
                stuInfo.setSName(firstNames[i1]+lastNames[i2]);
                stuInfo.setSClass("20"+sclass[i]);
                stuInfo.setSSex(sex[random.nextInt(2)]);
                stuInfo.setSColleges("软件学院");
                stuInfo.setSTel("139399"+String.valueOf(random.nextInt(80001)+10000));
                stuInfos.add(stuInfo);
            }
            stuInfoMapper.insertBatchSomeColumn(stuInfos);
            stuInfos.clear();
        }
    }

    @Test
    void test(){
        List<CourseInfo> courseInfos = courseInfoMapper.selectList(null);
    }
    @Test
    void test01() {
        List<CourseInfo> courseInfos = courseInfoMapper.selectList(null);
        List<StuInfo> stuInfos = stuInfoMapper.selectList(null);
        Random random = new Random();
        for (int i = 0; i < courseInfos.size(); i++) {
            int finalI = i;
            List<CourseScore> collect = stuInfos.stream().map(x -> {
                CourseScore courseScore = new CourseScore();
                courseScore.setSId(x.getSId());
                courseScore.setSName(x.getSName());
                courseScore.setCName(courseInfos.get(finalI).getCName());
                courseScore.setDalyScore(Double.valueOf(String.format("%.2f",random.nextDouble()*41+50)));
                courseScore.setTestScore(Double.valueOf(String.format("%.2f",random.nextDouble()*51+50)));
                courseScore.setFinalScore(Double.valueOf(String.format("%.2f",courseScore.getDalyScore()*0.3+courseScore.getTestScore()*0.7)));
                return courseScore;
            }).collect(Collectors.toList());
            courseScoreMapper.insertBatchSomeColumn(collect);
        }

    }

}
