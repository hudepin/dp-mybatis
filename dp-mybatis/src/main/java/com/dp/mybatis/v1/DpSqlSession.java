package com.dp.mybatis.v1;

/**
 *
 */
public class DpSqlSession {
    private DpConfiguration configuration;
    private Executor executor;

    public DpSqlSession(DpConfiguration configuration, Executor executor) {
        this.configuration = configuration;
        this.executor = executor;
    }

    /**
     * 获取mapper
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T getMapper(Class<T> clazz){
        return configuration.getMapper(clazz,this);
    }
    public <T> T selectOne(String statement,String parameter){
        return executor.query(statement,parameter);
    }
}
