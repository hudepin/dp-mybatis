package com.dp.mybatis.v2.plugin;


import java.util.ArrayList;
import java.util.List;

/**
 * <p>Description:</p>
 * Created with IDEA
 * author:hudepin
 * createTime:2018/5/14 16:40
 */
public class InterceptorChain {
    private final List<Interceptor> interceptors = new ArrayList<Interceptor>();
    public Object pluginAll(Object target){
        for(Interceptor i:interceptors){
            target = i.plugin(target);
        }
        return target;
    }
    public void addInterceptor(Interceptor in){
        interceptors.add(in);
    }
    public List<Interceptor> getInterceptors(){
        return interceptors;
    }

}
