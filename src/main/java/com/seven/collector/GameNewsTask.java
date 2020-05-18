package com.seven.collector;

import us.codecraft.webmagic.Spider;

/**
 * @Description: TODO
 * @Author chendongdong
 * @Date 2020/5/18 14:20
 * @Version V1.0
 **/
public class GameNewsTask {
    private static final String INDEX = "https://m.18183.com/" ;
    public static void main(String[] args) {
        Spider.create(new NewsTask()).addUrl(INDEX).thread(5).run();
    }
}
