package com.seven.collector.dao;

import com.seven.collector.bean.*;
import com.seven.collector.mapper.GameImageMapper;
import com.seven.collector.mapper.GameInfoMapper;
import com.seven.collector.mapper.GameNewsMapper;
import com.seven.collector.mybatis.SqlSessionTools;
import net.sf.cglib.beans.BeanCopier;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * @Description: TODO
 * @Author chendongdong
 * @Date 2020/5/15 18:15
 * @Version V1.0
 **/
public class MybatisDao {

    public static void insertDb(GameInfo gameInfo) {
        if (gameInfo == null) {
            return;
        }

        GameInfoDO infoDO = new GameInfoDO();
        BeanCopier copier = BeanCopier.create(GameInfo.class, GameInfoDO.class, false);
        copier.copy(gameInfo, infoDO, null);
        List<String> images = gameInfo.getImages();
        SqlSession sqlSession = SqlSessionTools.openSqlSession(false);
        try {
            GameInfoMapper infoMapper = sqlSession.getMapper(GameInfoMapper.class);
           Object object= infoMapper.selectByGameId(infoDO.getGameId());
           if (object!=null){
               System.out.println(gameInfo.getTitle()+":游戏已经存在，忽略添加");
               return;
           }
            int i = infoMapper.insertGameInfo(infoDO);
            if (i > 0 && images != null && images.size() > 0) {
                GameImageMapper gameImageMapper = sqlSession.getMapper(GameImageMapper.class);
                for (String url : images) {
                    GameImageDO imageDO = new GameImageDO();
                    imageDO.setUrl(url);
                    imageDO.setTargetId(infoDO.getId());
                    imageDO.setType(1);
                    gameImageMapper.insertGameImage(imageDO);
                }
            }
            System.out.println(gameInfo.getTitle()+":游戏数据入库成功 success");
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    public static void insertNewsDb(GameNews gameNews) {
        if (gameNews == null) {
            return;
        }
        GameNewsDO newsDO = new GameNewsDO();
        BeanCopier copier = BeanCopier.create(GameNews.class, GameNewsDO.class, false);
        copier.copy(gameNews, newsDO, null);
        List<String> images = gameNews.getImages();
        SqlSession sqlSession = SqlSessionTools.openSqlSession(false);
        try {
            GameNewsMapper newsMapper = sqlSession.getMapper(GameNewsMapper.class);
            Object object= newsMapper.selectByNewsId(newsDO.getNewsId());
            if (object!=null){
                System.out.println(newsDO.getTitle()+":新闻已存在，忽略添加");
                return;
            }
            int i = newsMapper.insertGameNews(newsDO);
            if (i > 0 && images != null && images.size() > 0) {
                GameImageMapper gameImageMapper = sqlSession.getMapper(GameImageMapper.class);
                for (String url : images) {
                    GameImageDO imageDO = new GameImageDO();
                    imageDO.setUrl(url);
                    imageDO.setTargetId(newsDO.getId());
                    imageDO.setType(2);
                    gameImageMapper.insertGameImage(imageDO);
                }
            }
            System.out.println(gameNews.getTitle()+":新闻数据入库成功 success");
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }
}
