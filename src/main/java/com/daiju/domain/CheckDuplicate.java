package com.daiju.domain;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.spire.doc.fields.TextRange;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author WDY
 * @Date 2020-12-08 9:02
 * @Description TODO
 */
@Component
public class CheckDuplicate {

    @Value("${spring.proxyUrl}")
    private String proxyUrl;

    ConcurrentHashMap<String, String> proxyMap = new ConcurrentHashMap<>();
    /**
     * 网络资源查询
     * @param wd
     * @return
     * @throws IOException
     */
    public  Map<String, List<String>> internetSearch(String wd,int flagProxy) throws IOException {
        System.out.println("=======================>"+Thread.currentThread().getName());
        System.out.println(wd);
        System.out.println("<=======================");
        Proxy proxy = null;
        if(flagProxy==1){
            proxy = new Proxy(Type.HTTP, new InetSocketAddress(proxyMap.get("ip"),Integer.parseInt(proxyMap.get("port")) ));
        }
        Document document = Jsoup.connect("https://www.baidu.com/s")
                .data("wd", wd)
                .proxy(proxy)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4315.4 Safari/537.36")
                .get();
        Elements elementsByClass = document.getElementsByClass("c-abstract");
        List<String> allContents = new ArrayList<>();
        List<String> matchContents = new ArrayList<>();
        elementsByClass.forEach(x->{
            x.select("span").remove();
            allContents.add(x.text());
            matchContents.add(x.select("em").text());
        });
        Map<String, List<String>> results = new HashMap<>();
        results.put("matchContents", matchContents);
        results.put("allContents", allContents);
        return results;
    }

    /**
     * 查询结果进行比对
     * @param searchWorld
     * @param document
     * @throws IOException
     */
    public void checkSearch(List<String> searchWorld,com.spire.doc.Document document) throws IOException {
        int i ;
        for (i = 0; i < searchWorld.size(); i++) {
            Map<String, List<String>> searchResult = internetSearch(searchWorld.get(i),0);
            while(searchResult.get("matchContents").size()==0){
                proxyMap.clear();
                this.setProxy();
                searchResult = internetSearch(searchWorld.get(i),1);
            }
            List<String> matchContents = searchResult.get("matchContents");
            List<String> allContents = searchResult.get("allContents");
            for (int l=0;l<matchContents.size();l++) {
                if (matchContents.get(l).replaceAll("[\\pP\\p{Punct}]", "").length() >= 13) {
                    String allContent = allContents.get(l).replace("...","");
                    System.out.println(allContent);
                    int length = allContent.length();
                    int flag = length % 30 == 0 ? length / 30 : length / 30 + 1;
                    if(i+1+flag==searchWorld.size()){
                        flag++;
                    } else if (i + flag > searchWorld.size()) {
                        flag = searchWorld.size() - i;
                    }
                    for (int j = i; j < flag + i; j++) {
                        System.out.println("------------------------>"+Thread.currentThread().getName());
                        System.out.println(searchWorld.get(j));
                        System.out.println("<------------------------");
                        TextRange[] ranges = document.findString(searchWorld.get(j), true, false).getRanges();
                        for (TextRange se : ranges) {
                            se.getCharacterFormat().setTextColor(Color.RED);
                        }
                    }
                    if(i+1+flag>searchWorld.size()){
                        break;
                    }
                    i+=flag-1;
                    break;
                }
            }

        }
    }

    /**
     * 段落划分
     * @param paragraph
     * @return
     */
    public List<String> divideSentences(String paragraph){
        List<String> sub = new ArrayList<>();
        paragraph.replaceAll("　　| {2}", "");
        System.out.println(paragraph);
        int length = paragraph.length();
        int j;
        if (paragraph.length() < 30) {
            sub.add(paragraph.substring(0, paragraph.length()));
        } else {
            for (j = 0; j < length - 30; j += 30) {
                sub.add(paragraph.substring(j, j + 30));
            }
            if ((paragraph.substring(j, length)).length() < 9) {
                sub.remove(sub.size() - 1);
                sub.add(paragraph.substring(j - 30, length));
            } else {
                sub.add(paragraph.substring(j, length));
            }
        }
        return sub;
    }

    /**
     * 设置代理ip
     */
    public void setProxy() throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(proxyUrl);
        CloseableHttpResponse res = httpClient.execute(httpGet);
        String s = EntityUtils.toString(res.getEntity());
        JSONObject resJson = JSON.parseObject(s);
        JSONArray msg = resJson.getJSONArray("msg");
        JSONObject jsonObject = msg.getJSONObject(0);
        String ip = jsonObject.getString("ip");
        String port = jsonObject.getString("port");
        proxyMap.put("ip", ip);
        proxyMap.put("port", port);
    }
}
