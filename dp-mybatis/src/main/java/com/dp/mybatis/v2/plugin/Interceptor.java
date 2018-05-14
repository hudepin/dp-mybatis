package com.dp.mybatis.v2.plugin;



import java.util.Properties;

/**
 * <p>Description:</p>
 * Created with IDEA
 * author:hudepin
 * createTime:2018/5/14 16:30
 */
public interface Interceptor {
    Object intercept(Invocation invocation) throws Throwable;

    Object plugin(Object target);

    void setProperties(Properties properties);
}
