package com.seven.collector.enums;

/**
 * @Description: TODO
 * @Author chendongdong
 * @Date 2020/5/15 10:30
 * @Version V1.0
 **/
public enum  GameTypeEnum {
    JSBY(1, "角色扮演"),
    SJQZ(2, "射击枪战"),
    CLZQ(3, "策略战棋"),
    MYJY(4, "模拟经营"),
    XXYZ(5, "休闲益智"),
    TYYD(6, "体育运动"),
    DZCG(7, "动作闯关"),
    SCJS(8, "赛车竞速"),
    KPYX(9, "卡牌游戏"),
    YYWD(10, "音乐舞蹈"),
    QPYX(11, "棋牌游戏"),
    MXJM(12, "冒险解迷");

    private Integer code;
    private String node;


    GameTypeEnum(Integer code, String node) {
        this.code = code;
        this.node = node;
    }

    public Integer getCode() {
        return code;
    }

    public String getNode() {
        return node;
    }

}
