package com.dp.mybatis.v2.binding;

import com.dp.mybatis.entity.Test;

import java.util.HashMap;
import java.util.Map;

public class MapperRegister {

    public static final String nameSpace="com.dp.mybatis.v1.TestMapper";
    public static final Map<String,MapperData> methodSqlMappings = new HashMap<String,MapperData>();
    static {
        methodSqlMappings.put("com.dp.mybatis.mapper.TestMapper.selectByPrimaryKey",new MapperData("select * from test where id=%d",Test.class));
    }
    public void addMapper(String methodKey,String sql,Class<?> type){
        methodSqlMappings.put(methodKey,new MapperData(sql,type));
    }
    public void clear(){
        methodSqlMappings.clear();
    }

}
