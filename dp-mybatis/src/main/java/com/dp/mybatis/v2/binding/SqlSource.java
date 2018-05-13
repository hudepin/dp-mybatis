package com.dp.mybatis.v2.binding;

public interface SqlSource {
    BindSql getBindSql(Object parameter);
}
