package com.seven.collector.bean;

import java.util.List;

/**
 * @Description: TODO
 * @Author chendongdong
 * @Date 2020/5/15 11:12
 * @Version V1.0
 **/
public class GameInfo {
    private Long id;
    private Long gameId;
    private String title;
    private String icon;
    private String type;
    private Integer typeId;

    //下载量
    private Integer downNum=0;
    //点赞量
    private Integer likeNum=0;
    //浏览量
    private Integer viewNum=0;
    //评分
    private Integer score=0;
    //星及
    private Integer starLevel=5;
    //android
    private String androidFileSize;
    private String androidDownUrl;
    //ios
    private String iosFileSize;
    private String iosDownUrl;
    //详情
    private String content;
    //图片
    private List<String> images;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getDownNum() {
        return downNum;
    }

    public void setDownNum(Integer downNum) {
        this.downNum = downNum;
    }

    public Integer getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }

    public Integer getViewNum() {
        return viewNum;
    }

    public void setViewNum(Integer viewNum) {
        this.viewNum = viewNum;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getStarLevel() {
        return starLevel;
    }

    public void setStarLevel(Integer starLevel) {
        this.starLevel = starLevel;
    }

    public String getAndroidFileSize() {
        return androidFileSize;
    }

    public void setAndroidFileSize(String androidFileSize) {
        this.androidFileSize = androidFileSize;
    }

    public String getAndroidDownUrl() {
        return androidDownUrl;
    }

    public void setAndroidDownUrl(String androidDownUrl) {
        this.androidDownUrl = androidDownUrl;
    }

    public String getIosFileSize() {
        return iosFileSize;
    }

    public void setIosFileSize(String iosFileSize) {
        this.iosFileSize = iosFileSize;
    }

    public String getIosDownUrl() {
        return iosDownUrl;
    }

    public void setIosDownUrl(String iosDownUrl) {
        this.iosDownUrl = iosDownUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
