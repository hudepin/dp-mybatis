package com.dp.mybatis.v2.executor;

import com.dp.mybatis.v2.binding.MapperData;
import com.dp.mybatis.v2.config.DpConfiguration;
import com.dp.mybatis.v2.statement.StatementHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DpCachingExecutor implements Executor {
    private DpConfiguration configuration;
    private DpSimpleExecutor delegate;
    private Map<String,Object> localCache = new ConcurrentHashMap<String,Object>();

    public DpCachingExecutor(DpSimpleExecutor delegate) {
        this.delegate = delegate;
    }

    @Override
    public <T> T query(MapperData mapperData, Object parameter) {
        StatementHandler statementHandler = new StatementHandler(configuration);
        String key = mapperData.getSql();
        if(localCache.get(key)!=null){
            return(T) localCache.get(key);
        }
        Object obj = statementHandler.query(mapperData,parameter);
        localCache.put(key,obj);
        return  (T)obj;
    }
}
