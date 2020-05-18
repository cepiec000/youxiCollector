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
import us.codecraft.webmagic.processor.PageProcessor;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: TODO
 * @Author chendongdong
 * @Date 2020/5/18 14:27
 * @Version V1.0
 **/
public class NewsTask implements PageProcessor {

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
        if (page.getUrl().get().matches(NEWS_PAGE_MATCHES)) {
            JSONObject object = JSON.parseObject(page.getRequest().getExtra("json").toString());
            GameNews gameNews = new GameNews();
            gameNews.setNewsId(object.getLong("id"));
            gameNews.setShortTitle(object.getString("shorttitle"));
            gameNews.setDescription(object.getString("description"));
            gameNews.setSource("18183");
            gameNews.setTitle(object.getString("title"));
            gameNews.setSourceUrl(object.getString("url"));
            gameNews.setTypeId(object.getInteger("typeid"));
            gameNews.setTypeName(object.getString("type_name"));
            gameNews.setPubTime(ToolUtil.convertTimeToDate(Long.valueOf(object.getString("sortrank") + "000")));
            List<String> images = new ArrayList<>();
            JSONArray jsonArray = object.getJSONArray("images");
            for (int i = 0; i < jsonArray.size(); i++) {
                images.add(jsonArray.get(i).toString());
            }
            //内容
            List<String> cList = page.getHtml().xpath("//div[@class=\"u-content-box\"]/p").all();
            gameNews.setContent(ToolUtil.getContent(cList));
            gameNews.setImages(images);
            //入库
            MybatisDao.insertNewsDb(gameNews);
        } else if (page.getUrl().get().matches(JSON_PAGE_MATCHES)) {
            String body = page.getHtml().xpath("body").replace("<body>", "").replace("</body>", "").toString();
            JSONObject object = JSON.parseObject(body);
            JSONArray array = object.getJSONArray("list");
            if (array != null && array.size() > 0) {
                for (int i = 0; i < array.size(); i++) {
                    JSONObject temp = JSON.parseObject(array.get(i).toString());
                    String url = temp.getString("url");
                    Request request = new Request(url);
                    request.putExtra("json", temp);
                    page.addTargetRequest(request);
                }
            }
            nextPageNews(page, object.getJSONObject("page").getString("s"));
        } else if (page.getUrl().get().matches(INDEX_PAGE_MATCHES)) {
            //进入下一页
            nextPageNews(page, null);
        }
    }

    @Override
    public Site getSite() {
        return site;
    }


    private void nextPageNews(Page page, String timeStr) {
        Long currentTimeMillis = System.currentTimeMillis();
        if (timeStr == null) {
            timeStr = currentTimeMillis.toString().substring(0, 10);
        }
        String url = MessageFormat.format(URL, timeStr);
        String url2 = MessageFormat.format(URL2, timeStr);
        String url3 = MessageFormat.format(URL3, timeStr);
        String url4 = MessageFormat.format(URL4, timeStr);
        List<String> requests = new ArrayList<>();
        requests.add(url);
        requests.add(url2);
        requests.add(url3);
        requests.add(url4);
        page.addTargetRequests(requests);

    }
}
