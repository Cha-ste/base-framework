package com.ocean.mapper;

import com.ocean.entity.MybatisTest;
import java.util.HashMap;
import java.util.List;

public interface MybatisTestMapper {

    int deleteByPrimaryKey(String id);

    int insert(MybatisTest record);

    int insertSelective(MybatisTest record);

    MybatisTest selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MybatisTest record);

    int updateByPrimaryKey(MybatisTest record);

    int getCount();

    List<MybatisTest> query(HashMap<String, Object> paramMap);
    int queryCount(HashMap<String, Object> paramMap);

}