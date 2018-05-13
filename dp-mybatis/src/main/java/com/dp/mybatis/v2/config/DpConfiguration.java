package com.dp.mybatis.v2.config;


import com.dp.mybatis.v2.mapper.MapperProxy;
import com.dp.mybatis.v2.session.DpSqlSession;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class DpConfiguration {
    public <T> T getMapper(Class<T> clazz,DpSqlSession sqlSession) {
        return (T)Proxy.newProxyInstance(clazz.getClassLoader(),new Class<?>[]{clazz},new MapperProxy(sqlSession));
    }

    /**
     * 构建解析xml映射文件，目前先had code，不影响主流程
     */
    public static class TestMapperXml{
        public static final String nameSpace="com.dp.mybatis.v1.TestMapper";
        public static final Map<String,String> methodSqlMappings = new HashMap<String,String>();
        static {
            methodSqlMappings.put("selectByPrimaryKey","select * from test where id=%d");
        }
    }
}
