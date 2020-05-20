package com.seven.collector;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.seven.collector.bean.GameNews;
import com.seven.collector.dao.MybatisDao;
import com.seven.collector.utils.ToolUtil;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description: TODO
 * @Author chendongdong
 * @Date 2020/5/18 14:27
 * @Version V1.0
 **/
public class TestTask implements PageProcessor {

    private Site site = Site.me().setRetryTimes(5).setSleepTime(1000).setTimeOut(10000);
    private static final String URL = "https://api-mcms.18183.com/web/wap-home_{0}_1.json" ;
    private static final String URL2 = "https://api-mcms.18183.com/web/list_234_1_{0}.json" ;
    private static final String URL3 = "https://api-mcms.18183.com/web/list_218_1_{0}.json" ;
    private static final String URL4 = "https://api-mcms.18183.com/web/list_219_1_{0}.json" ;
    private static final String INDEX_PAGE_MATCHES = "https://m.18183.com/" ;
    private static final String NEWS_PAGE_MATCHES = "https://m.18183.com/news/yxxw/.*" ;
    private static final String JSON_PAGE_MATCHES = "https://api-mcms.18183.com/web/.*" ;

    @Override
    public void process(Page page) {

            //内容
            String content = page.getHtml().xpath("//div[@class=\"u-content-box\"]").get();
            System.out.println(content);

    }

    @Override
    public Site getSite() {
        return site;
    }


    public static void main(String[] args) {
        Spider.create(new TestTask()).addUrl("https://m.18183.com/news/yxxw/202005/2845060.html").thread(5).run();
    }
}
