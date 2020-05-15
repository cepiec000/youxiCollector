package com.seven.collector.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

/**
 * @Description: TODO
 * @Author chendongdong
 * @Date 2020/5/15 17:51
 * @Version V1.0
 **/
public class SqlSessionTools {
    public static SqlSession openSqlSession(boolean auto) {
        try {
            // 定义配置文件，相对路径，文件直接放在resources目录下
            String resource = "configuration.xml";
            // 读取文件字节流
            InputStream inputStream = Resources.getResourceAsStream(resource);

            // mybatis 读取字节流，利用XMLConfigBuilder类解析文件
            // 将xml文件解析成一个 org.apache.ibatis.session.Configuration 对象
            // 然后将 Configuration 对象交给 SqlSessionFactory 接口实现类 DefaultSqlSessionFactory 管理
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            // openSession 有多个重载方法， 比较重要几个是
            // 1 是否默认提交 SqlSession openSession(boolean autoCommit)
            // 2 设置事务级别 SqlSession openSession(TransactionIsolationLevel level)
            // 3 执行器类型   SqlSession openSession(ExecutorType execType)
            SqlSession sqlSession = sqlSessionFactory.openSession(auto);
            return sqlSession;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }
}
