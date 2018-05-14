package com.dp.mybatis.v2.result;

import com.dp.mybatis.v2.config.DpConfiguration;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultSetHandler {
    private DpConfiguration configuration;

    public ResultSetHandler(DpConfiguration configuration) {
        this.configuration=configuration;
    }

    public <E> E handler(ResultSet rs, Class type) {
        DefaultObjectFactory objectFactory = new DefaultObjectFactory();
        Object obj = objectFactory.create(type);
        try {
            while(rs.next()){
                int i=0;
                for(Field field:type.getDeclaredFields()){
                    setValue(obj,rs,i,field);
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (E)obj;
    }

    private void setValue(Object obj, ResultSet rs, int i, Field field) {
        try {
            Method m = obj.getClass().getMethod("set"+upperCaseFirst(field.getName()),field.getType());
            try {
                m.invoke(obj,getResult(field,rs));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    private Object getResult(Field field, ResultSet rs) throws SQLException {
        Class<?> fieldType = field.getType();
        if(fieldType==Integer.class){
            return rs.getInt(field.getName());
        }else if(fieldType==String.class){
            return rs.getString(field.getName());
        }
        return null;
    }

    private String upperCaseFirst(String name) {
        return name.substring(0,1).toUpperCase()+name.substring(1);
    }
}
