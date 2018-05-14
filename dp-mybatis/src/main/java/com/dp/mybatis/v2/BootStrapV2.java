package com.dp.mybatis.v2;

import com.dp.mybatis.mapper.TestMapper;
import com.dp.mybatis.v2.config.DpConfiguration;
import com.dp.mybatis.v2.executor.ExecutorFactory;
import com.dp.mybatis.v2.session.DpSqlSession;

/**
 * <p>Description:</p>
 * Created with IDEA
 * author:hudepin
 * createTime:2018/5/14 14:55
 */
public class BootStrapV2 {
    public static void main(String[] args) {
        DpConfiguration configuration = new DpConfiguration();
        //start annotation方式实现
        configuration.setScanPath("com.dp.mybatis.mapper");
        configuration.build();
        //end annotation方式实现
        DpSqlSession sqlSession = new DpSqlSession(configuration, ExecutorFactory.DEFAULT(configuration));
        TestMapper mapper = sqlSession.getMapper(TestMapper.class);
        System.out.println(mapper.selectByPrimaryKey(1));



    }
}
