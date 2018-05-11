package com.dp.mybatis.v1;

/**
 * <p>Description:</p>
 * Created with IDEA
 * author:hudepin
 * createTime:2018/5/11 10:43
 */
public interface Executor {
    <T> T query(String statement, String parameter);
}
