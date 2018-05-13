package com.dp.mybatis.v2.mapper;


import com.dp.mybatis.v2.config.DpConfiguration;
import com.dp.mybatis.v2.session.DpSqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * <p>Description:</p>
 * Created with IDEA
 * author:hudepin
 * createTime:2018/5/11 10:46
 */
public class MapperProxy implements InvocationHandler{
    private DpSqlSession sqlSession;

    public MapperProxy(DpSqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(method.getDeclaringClass().getName().equals(DpConfiguration.TestMapperXml.nameSpace)){
            String paramete = String.valueOf(args[0]);
            String statement =DpConfiguration.TestMapperXml.methodSqlMappings.get(method.getName());
            return sqlSession.selectOne(statement,paramete);
        }
        return method.invoke(this,args);
    }
}
