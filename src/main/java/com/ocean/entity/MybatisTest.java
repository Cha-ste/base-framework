/*
*
* MybatisTest.java
* Copyright(C) 2017-2020 fendo公司
* @date 2019-08-13
*/
package com.ocean.entity;

public class MybatisTest {
    private String id;

    private String testname;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTestname() {
        return testname;
    }

    public void setTestname(String testname) {
        this.testname = testname == null ? null : testname.trim();
    }
}