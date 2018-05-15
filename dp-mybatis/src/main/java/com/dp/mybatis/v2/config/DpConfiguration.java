package com.dp.mybatis.v2.config;


import com.dp.mybatis.v2.annotation.Select;
import com.dp.mybatis.v2.binding.MapperData;
import com.dp.mybatis.v2.binding.MapperProxy;
import com.dp.mybatis.v2.binding.MapperRegister;
import com.dp.mybatis.v2.plugin.Interceptor;
import com.dp.mybatis.v2.plugin.InterceptorChain;
import com.dp.mybatis.v2.result.ResultSetHandler;
import com.dp.mybatis.v2.session.DpSqlSession;
import com.dp.mybatis.v2.statement.StatementHandler;
import org.springframework.util.StringUtils;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class DpConfiguration {
    private MapperRegister mapperRegister = new MapperRegister();
    /**
     * plugin 拦截器链
     */
    protected final InterceptorChain interceptorChain = new InterceptorChain();
    private String scanPath;
    public <T> T getMapper(Class<T> clazz,DpSqlSession sqlSession) {
        return (T)Proxy.newProxyInstance(clazz.getClassLoader(),new Class<?>[]{clazz},new MapperProxy(sqlSession));
    }
    public MapperData getMapperData(String key){
        return (MapperData) mapperRegister.methodSqlMappings.get(key);
    }
    public void setScanPath(String scanPath){
        this.scanPath = scanPath;
    }
    public void addInterceptor(Interceptor interceptor) {
        interceptorChain.addInterceptor(interceptor);
    }

    public InterceptorChain getInterceptorChain(){
        return this.interceptorChain;
    }

    public void build(){
        if(!StringUtils.isEmpty(scanPath)){
            mapperRegister.clear();
            ClassLoader loader = this.getClass().getClassLoader();
            URL url = loader.getResource(scanPath.replace(".","\\"));
            try {
                URI uri = url.toURI();
                File file = new File(uri);
                File[] files = file.listFiles();
                for(File f:files){
                    String fname= f.getName();
                    if(!fname.endsWith(".class")){
                        continue;
                    }
                    fname =fname.substring(0,fname.length()-6);
                    String fullName = scanPath+"."+fname;
                    try {
                      Class<?> clazz =   Class.forName(fullName);
                      applyAnnotation(clazz);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }

                }
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }

        }
    }

    private void applyAnnotation(Class<?> clazz) {
        Method[] methods = clazz.getMethods();
        for(Method m:methods){
            Select select = m.getAnnotation(Select.class);
            if(select!=null){
               String sql =  select.value()[0];
               String key = clazz.getName()+"."+m.getName();
               mapperRegister.addMapper(key,sql,m.getReturnType());
            }
        }
    }

}
