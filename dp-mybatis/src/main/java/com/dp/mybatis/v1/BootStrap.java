package com.dp.mybatis.v1;

import com.dp.mybatis.entity.Test;
import com.dp.mybatis.mapper.TestMapper;

/**
 * <p>Description:</p>
 * Created with IDEA
 * author:hudepin
 * createTime:2018/5/11 10:42
 */
public class BootStrap {
    public static void main(String[] args) {
        DpSqlSession sqlSession = new DpSqlSession(new DpConfiguration(),new DpSimpleExecutor());
        TestMapper testMapper  = sqlSession.getMapper(TestMapper.class);
        Test test  = testMapper.selectByPrimaryKey(1);
        System.out.println(test);
    }
}
