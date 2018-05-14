package com.dp.mybatis.v2.executor;

import com.dp.mybatis.v2.binding.MapperData;

/**
 * <p>Description:</p>
 * Created with IDEA
 * author:hudepin
 * createTime:2018/5/11 10:43
 */
public interface Executor {
    /**
     *
     * @param mapperData
     * @param parameter
     * @param <T>
     * @return
     */
    <T> T query(MapperData mapperData, Object parameter);
}
