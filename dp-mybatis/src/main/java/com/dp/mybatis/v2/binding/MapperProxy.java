package com.dp.mybatis.v2.binding;


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
        MapperData mapperData = sqlSession.getConfiguration().getMapperData(method.getDeclaringClass().getName()+"."+method.getName());
        String parameter = String.valueOf(args[0]);
        if(mapperData!=null)
        return sqlSession.selectOne(mapperData,parameter);
        return method.invoke(this,args);
    }
}
