package com.dp.mybatis.entity;

import java.io.Serializable;

/**
 * <p>Description:</p>
 * Created with IDEA
 * author:hudepin
 * createTime:2018/5/11 10:42
 */

public class Test implements Serializable{
    public Test() {

    }
    private Integer id;

    private Integer nums;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNums() {
        return nums;
    }

    public void setNums(Integer nums) {
        this.nums = nums;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                ", nums=" + nums +
                ", name='" + name + '\'' +
                '}';
    }
}
