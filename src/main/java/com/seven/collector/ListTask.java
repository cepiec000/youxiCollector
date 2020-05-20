package com.seven.collector;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.seven.collector.bean.GameInfo;
import com.seven.collector.dao.MybatisDao;
import com.seven.collector.enums.GameTypeEnum;
import com.seven.collector.utils.ToolUtil;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: TODO
 * @Author chendongdong
 * @Date 2020/5/15 15:12
 * @Version V1.0
 **/
public class ListTask implements PageProcessor {
    private Site site = Site.me().setRetryTimes(5).setSleepTime(1000).setTimeOut(10000);
    private final static String LIST_PAGE_URL = "https://m.18183.com/ku/list.*" ;
    private final static String INFO_PAGE_URL_HTTP = "http://m.18183.com/ku/.*" ;
    private final static String INFO_PAGE_URL_HTTPS = "https://m.18183.com/ku/.*" ;
    private final static String INFO_PAGE_URL2_HTTP = "https://m.18183.com/newgames/.*" ;
    private final static String INFO_PAGE_URL3_HTTPS = "https://m.18183.com/down/.*" ;
    private final static String INFO_PAGE_URL2 = "http://m.18183.com/newgames/.*" ;
    private final static String INFO_PAGE_URL3 = "http://m.18183.com/down/.*" ;
    private final static String JSON_API_URL = "https://down-statistics.18183.com/down/.*" ;

    private GameTypeEnum typeEnum;

    public ListTask(GameTypeEnum typeEnum) {
        this.typeEnum = typeEnum;
    }

    @Override
    public void process(Page page) {
        //从页面发现后续的url地址加入到队列中去
        if (page.getUrl().get().matches(LIST_PAGE_URL)) {
            List<String> urls = page.getHtml().xpath("//*[@id=\"j_game_list\"]/li/figure/@data-url").all();
            page.addTargetRequests(urls);
        } else if (page.getUrl().get().matches(INFO_PAGE_URL_HTTP) || page.getUrl().get().matches(INFO_PAGE_URL_HTTPS)) {
            //游戏详情
            List<String> images = page.getHtml().xpath("//div[@class=\"swiper-slide\"]/img/@data-url").all();
            List<String> cPlist = page.getHtml().xpath("//div[@class=\"shrinkRevealBox\"]/p").all();
            //相关游戏下载
            List<String> xgyouxiList = page.getHtml().xpath("//ul[@class=\"m-related-game f-def\"]/li/a").links().all();
            List<String> xgyx = new ArrayList<String>();
            if (xgyouxiList != null && xgyouxiList.size() > 0) {
                for (String s : xgyouxiList) {
                    xgyx.add(s.replace("www.18183.com", "m.18183.com"));
                }
            }

            String jsonUrl = "https://down-statistics.18183.com/down/pack?game_id=" + ToolUtil.getGameKuId(page.getHtml().toString()) + "&v=1220" ;
            Request request = new Request(jsonUrl).putExtra("images", images).putExtra("cPlist", cPlist);
            page.addTargetRequest(request);
            page.addTargetRequests(xgyx);
        } else if (page.getUrl().get().matches(INFO_PAGE_URL2)
                || page.getUrl().get().matches(INFO_PAGE_URL3)
                || page.getUrl().get().matches(INFO_PAGE_URL2_HTTP)
                || page.getUrl().get().matches(INFO_PAGE_URL3_HTTPS)) {
            //相关游戏详情
            List<String> images = page.getHtml().xpath("//div[@class=\"swiper-slide\"]/img/@data-url").all();
            if (images == null || images.size() == 0) {
                images = page.getHtml().xpath("//div[@class=\"swiper-slide\"]/a/img/@data-url").all();
            }
            String content = page.getHtml().xpath("//div[@class=\"shrinkRevealBox\"]").get();
            String gameId = ToolUtil.getGameKuId(page.getHtml().toString());
            String jsonUrl = "https://down-statistics.18183.com/down/pack?game_id=" + gameId + "&v=" + gameId;
            Request request = new Request(jsonUrl);
            if (images != null && images.size() > 0) {
                request.putExtra("images", images);
            }
                request.putExtra("content", content);
            page.addTargetRequest(request);
        } else if (page.getUrl().get().matches(JSON_API_URL)) {
            GameInfo gameInfo = new GameInfo();
            List<String> images = (List<String>) page.getRequest().getExtra("images");
            String content = (String) page.getRequest().getExtra("content");
            String body = page.getHtml().xpath("body").replace("<body>", "").replace("</body>", "").toString();
            JSONObject object = JSON.parseObject(body);
            JSONObject data = object.getJSONObject("data");
            JSONObject game_info = data.getJSONObject("game_info");
            JSONObject androidInfo = data.getJSONObject("android_down");
            JSONObject iosInfo = data.getJSONObject("ios_down");
            try {
                //如果IOS 跟 android下载地址都为空 就不储存
                boolean isDow = (androidInfo == null && iosInfo == null);
                if (isDow) {
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            gameInfo.setGameId(game_info.getLong("game_id"));
            gameInfo.setTitle(game_info.getString("title"));
            gameInfo.setIcon(game_info.getString("icon"));
            gameInfo.setContent(content);
            gameInfo.setImages(images);
            gameInfo.setDownNum(game_info.getInteger("down_num"));
            gameInfo.setViewNum(game_info.getInteger("view_num"));
            gameInfo.setAndroidDownUrl(androidInfo.getString("down_url"));
            gameInfo.setAndroidFileSize(androidInfo.getString("file_size"));
            gameInfo.setIosDownUrl(iosInfo.getString("down_url"));
            gameInfo.setIosFileSize(iosInfo.getString("file_size"));
            gameInfo.setTypeId(typeEnum.getCode());
            gameInfo.setType(typeEnum.getNode());
            MybatisDao.insertDb(gameInfo);
        }

    }

    @Override
    public Site getSite() {
        return site;
    }


}
