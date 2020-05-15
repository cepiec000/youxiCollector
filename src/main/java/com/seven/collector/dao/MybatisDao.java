package com.seven.collector.dao;

import com.seven.collector.bean.GameImageDO;
import com.seven.collector.bean.GameInfo;
import com.seven.collector.bean.GameInfoDO;
import com.seven.collector.mapper.GameImageMapper;
import com.seven.collector.mapper.GameInfoMapper;
import com.seven.collector.mybatis.SqlSessionTools;
import net.sf.cglib.beans.BeanCopier;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
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
            int i = infoMapper.insertGameInfo(infoDO);
            if (i > 0 && images != null && images.size() > 0) {
                GameImageMapper gameImageMapper = sqlSession.getMapper(GameImageMapper.class);
                for (String url : images) {
                    GameImageDO imageDO = new GameImageDO();
                    imageDO.setUrl(url);
                    imageDO.setGameId(infoDO.getId());
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

}
