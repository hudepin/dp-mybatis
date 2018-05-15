package com.dp.mybatis.v2;

import com.dp.mybatis.v2.binding.MapperData;
import com.dp.mybatis.v2.executor.Executor;
import com.dp.mybatis.v2.plugin.*;
import com.dp.mybatis.v2.result.ResultSetHandler;
import com.dp.mybatis.v2.statement.StatementHandler;

import java.util.Iterator;
import java.util.Properties;

/**
 * <p>Description:</p>
 * Created with IDEA
 * author:hudepin
 * createTime:2018/5/15 10:00
 */
@DpIntecepters({@Signature(type = Executor.class, method = "query", args = {MapperData.class, Object.class}),
        @Signature(type = StatementHandler.class,method ="query",args = {MapperData.class, Object.class})
}
)
public class SqlLogPlugin implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("SqlLogPlugin start intercept target:[" + invocation.getTarget().getClass().getName()+"],method:["+invocation.getMethod().getName()+"],args:["+invocation.getArgs()+"]");
        if(invocation.getArgs()[0] instanceof  MapperData){
            System.out.println("sql:"+((MapperData)invocation.getArgs()[0]).getSql()+",parameter:"+invocation.getArgs()[1].toString());
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        try {
            //System.out.println("-----SqlLogPlugin start plugin wrap target = [" + target + "]");
            return DpPlugin.wrap(target, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void setProperties(Properties properties) {
        Iterator iterator = properties.keySet().iterator();
        while (iterator.hasNext()) {
            String keyValue = String.valueOf(iterator.next());
            System.out.println("- - - - -mybatis properties key:"+keyValue+"value:"+properties.getProperty(keyValue));
        }
    }
}
