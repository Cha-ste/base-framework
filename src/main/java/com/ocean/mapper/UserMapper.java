package com.ocean.mapper;

import com.ocean.entity.User;
import java.util.HashMap;
import java.util.List;

public interface UserMapper {

    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int getCount();

    List<User> query(HashMap<String, Object> paramMap);
    int queryCount(HashMap<String, Object> paramMap);

}