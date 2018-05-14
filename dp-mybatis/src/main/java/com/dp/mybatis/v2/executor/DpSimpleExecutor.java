package com.dp.mybatis.v2.executor;

import com.dp.mybatis.v2.binding.MapperData;
import com.dp.mybatis.v2.config.DpConfiguration;
import com.dp.mybatis.v2.statement.StatementHandler;

import java.sql.*;

/**
 * <p>Description:</p>
 * Created with IDEA
 * author:hudepin
 * createTime:2018/5/11 10:58
 */
public class DpSimpleExecutor implements Executor {
    private final DpConfiguration configuration;

    public DpSimpleExecutor(DpConfiguration configuration) {
        this.configuration = configuration;
    }
    /**
     *
     * @param mapperData sql and type mapping object
     * @param parameter 参数
     * @param <T>
     * @return
     */
    @Override
    public <T> T query(MapperData mapperData, Object parameter) {
        StatementHandler statementHandler = new StatementHandler(this.configuration);
       return (T) statementHandler.query(mapperData,parameter);

    }


}
