package com.dp.mybatis.v2.statement;

import com.dp.mybatis.v2.binding.MapperData;


public interface StatementHandler {

    public <E> E query(MapperData mapperData, Object parameter) ;
}
