package com.seven.collector;

import com.seven.collector.enums.GameTypeEnum;
import us.codecraft.webmagic.Spider;

import java.text.MessageFormat;

/**
 * @Description: TODO
 * @Author chendongdong
 * @Date 2020/5/15 15:11
 * @Version V1.0
 **/
public class CollectGameTask {
    private final static String seed = "https://m.18183.com/ku/list-0-{0}-0-0-0-{1}.html?q=";
    private final static String newGame="https://m.18183.com/ku/list-0-{0}-0-2-1-{1}.html";
    private final static String typeGame="https://m.18183.com/ku/list-0-0-{0}-2-0-{1}.html";
    public static void main(String[] args) {
        getGame();
        typeGame();

    }
    public static void getGame(){
        for (GameTypeEnum typeEnum : GameTypeEnum.values()) {
            final GameTypeEnum type = typeEnum;
            for (int i = 1; i <= 50; i++) {
                final String url = MessageFormat.format(newGame, String.valueOf(type.getCode()), String.valueOf(i));
                final String url1 = MessageFormat.format(seed, String.valueOf(type.getCode()), String.valueOf(i));
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Spider.create(new ListTask(type)).addUrl(url).thread(5).run();
                        Spider.create(new ListTask(type)).addUrl(url1).thread(5).run();
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
    public static void typeGame() {
        for (int i=1;i<=38;i++){
            for (int j=1;j<=50;j++){
                final String url = MessageFormat.format(typeGame, String.valueOf(i), String.valueOf(j));
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Spider.create(new ListTask2()).addUrl(url).thread(5).run();
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
