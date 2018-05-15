package com.dp.mybatis.v2.plugin;

import com.dp.mybatis.v2.SqlLogPlugin;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * <p>Description: dp plugin aop for inteceptor</p>
 * Created with IDEA
 * author:hudepin
 * createTime:2018/5/15 9:49
 */
public class DpPlugin implements InvocationHandler {
    private Object target;
    private Interceptor inteceptor;
    private Map<Class<?>, Set<Method>> signatureMaps = new HashMap<Class<?>, Set<Method>>();

    public DpPlugin(Object target, Interceptor inteceptor, Map<Class<?>, Set<Method>> signatureMaps) {
        this.inteceptor = inteceptor;
        this.target = target;
        this.signatureMaps = signatureMaps;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Set<Method> methods = signatureMaps.get(method.getDeclaringClass());
        if (methods != null && methods.contains(method)) {
            return inteceptor.intercept(new Invocation(target, method, args));
        }
        return method.invoke(proxy, args);
    }

    public static Object wrap(Object target, Interceptor interceptor) throws Exception {
        Map<Class<?>, Set<Method>> signatureMap = getSignatureMap(interceptor);
        Class<?>[] interfaces = getAllInterfaces(target.getClass(), signatureMap);
        if (interfaces != null && interfaces.length > 0) {
            return Proxy.newProxyInstance(target.getClass().getClassLoader(), interfaces, new DpPlugin(target, interceptor, signatureMap));
        }
        return target;
    }

    private static Class<?>[] getAllInterfaces(Class<?> type, Map<Class<?>, Set<Method>> signatureMap) {
        Set<Class<?>> interfaceSets = new HashSet<Class<?>>();
        while (type != null) {
            for (Class<?> c : type.getInterfaces()) {
                if (signatureMap.containsKey(c)) {
                    interfaceSets.add(c);
                }
            }
            type = type.getSuperclass();
        }
        return interfaceSets.toArray(new Class<?>[interfaceSets.size()]);
    }

    private static Map<Class<?>, Set<Method>> getSignatureMap(Interceptor intecepter) throws Exception {
        DpIntecepters intercepters = intecepter.getClass().getAnnotation(DpIntecepters.class);
        if (intercepters == null) {
            throw new Exception("no @DpIntecepters found");
        }
        Signature[] signatures = intercepters.value();
        Map<Class<?>, Set<Method>> signatureMaps = new HashMap<Class<?>, Set<Method>>();
        for (Signature sig : signatures) {
            Set<Method> methods = signatureMaps.get(sig.type());
            if (methods == null) {
                methods = new HashSet<Method>();
            }
            methods.add(sig.type().getMethod(sig.method(), sig.args()));
            signatureMaps.put(sig.type(), methods);
        }
        return signatureMaps;

    }
}
