package com.dp.mybatis.v2.plugin;

import java.lang.annotation.*;

/**
 * <p>Description:拦截器注解作用于Interceptor上面</p>
 * Created with IDEA
 * author:hudepin
 * createTime:2018/5/15 10:10
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface DpIntecepters {
    Signature[] value();
}
