package com.dp.mybatis.v2.executor;

import com.dp.mybatis.v1.Test;

import java.sql.*;

/**
 * <p>Description:</p>
 * Created with IDEA
 * author:hudepin
 * createTime:2018/5/11 10:58
 */
public class DpSimpleExecutor implements Executor {
    private static final String JDBC_URL="jdbc:mysql://localhost:3306/gp?useUnicode=true&characterEncoding=utf-8&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    /**
     *
     * @param statement sql
     * @param parameter 参数
     * @param <T>
     * @return
     */
    @Override
    public <T> T query(String statement, String parameter) {
        PreparedStatement ps = null;
        Connection con = null;
        ResultSet rs = null;
        Test test =new Test();
        String sql = String.format(statement,Integer.parseInt(parameter));
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(JDBC_URL,"root","123456");
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                test.setId(rs.getInt(1));
                test.setNums(rs.getInt(2));
                test.setName(rs.getString(3));
            }
            return (T)test;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if(null!= con){
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


}
