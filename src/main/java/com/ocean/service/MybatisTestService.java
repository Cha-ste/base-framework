package com.ocean.service;

import java.util.HashMap;
import java.util.List;
import com.ocean.entity.MybatisTest;
import com.github.pagehelper.PageInfo;


public interface MybatisTestService {

    MybatisTest getMybatisTest(String id);

    void save(MybatisTest model);

    void update(MybatisTest model);

    void del(String id);

    int getCount();

    PageInfo<MybatisTest> query(int currentPage, int itemsPerPage, HashMap<String, Object> paramMap);
    int queryCount(HashMap<String, Object> paramMap);

}