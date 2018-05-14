package com.dp.mybatis.mapper;

import com.dp.mybatis.entity.Test;
import com.dp.mybatis.v2.annotation.Select;

/**
 * <p>Description:</p>
 * Created with IDEA
 * author:hudepin
 * createTime:2018/5/11 10:42
 */
public interface TestMapper {
    @Select(value = "select * from test where id=%d")
    Test selectByPrimaryKey(Integer id);
}
