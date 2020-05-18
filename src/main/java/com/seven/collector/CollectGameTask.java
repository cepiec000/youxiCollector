package com.seven.collector;

import com.seven.collector.enums.GameTypeEnum;
import us.codecraft.webmagic.Spider;

import java.text.MessageFormat;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Description: TODO
 * @Author chendongdong
 * @Date 2020/5/15 15:11
 * @Version V1.0
 **/
public class CollectGameTask {
    private final static String seed = "https://m.18183.com/ku/list-0-{0}-0-0-0-{1}.html?q=";
    public static Queue<String> queue = new LinkedList<String>();
    public static void main(String[] args) {
        for (GameTypeEnum typeEnum : GameTypeEnum.values()) {
            final GameTypeEnum type = typeEnum;
            for (int i = 1; i <= 50; i++) {
                final String url = MessageFormat.format(seed, String.valueOf(type.getCode()), String.valueOf(i));
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Spider.create(new ListTask(type)).addUrl(url).thread(5).run();
                        try {
                            Thread.sleep(1000L);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                try {
                    Thread.sleep(2000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
