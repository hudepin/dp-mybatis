package com.dp.mybatis.v2.executor;


import com.dp.mybatis.v2.config.DpConfiguration;

/**
 * <p>Description:</p>
 * Created with IDEA
 * author:hudepin
 * createTime:2018/5/14 15:22
 */
public class ExecutorFactory {
    private static final String SIMPLE = "SIMPLE";
    private static final String CACHING = "CACHING";
    public static Executor DEFAULT(DpConfiguration configuration) {
        return get(SIMPLE, configuration);
    }
    public static Executor get(String key, DpConfiguration configuration) {
        if (SIMPLE.equalsIgnoreCase(key)) {
            return new DpSimpleExecutor(configuration);
        }
        if (CACHING.equalsIgnoreCase(key)) {
            return new DpCachingExecutor(new DpSimpleExecutor(configuration));
        }
        throw new RuntimeException("no executor found");
    }

    public enum ExecutorType {
        SIMPLE,CACHING
    }
}
