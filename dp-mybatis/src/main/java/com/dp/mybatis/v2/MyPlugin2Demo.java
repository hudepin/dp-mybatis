package com.dp.mybatis.v2;

import com.dp.mybatis.v2.binding.MapperData;
import com.dp.mybatis.v2.executor.Executor;
import com.dp.mybatis.v2.plugin.*;
import com.dp.mybatis.v2.statement.StatementHandler;

import java.util.Properties;

/**
 * <p>Description:</p>
 * Created with IDEA
 * author:hudepin
 * createTime:2018/5/15 16:02
 */
@DpIntecepters({@Signature(type = Executor.class,
        method = "query", args = {MapperData.class, Object.class})
}
)
public class MyPlugin2Demo implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("MyPlugin2Demo invocation = [" + invocation + "]");
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        try {
            return DpPlugin.wrap(target, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
