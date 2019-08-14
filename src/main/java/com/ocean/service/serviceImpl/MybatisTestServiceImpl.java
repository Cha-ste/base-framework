package com.ocean.service.serviceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import com.ocean.entity.MybatisTest;
import com.ocean.service.MybatisTestService;
import com.ocean.mapper.MybatisTestMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class MybatisTestServiceImpl implements MybatisTestService{

    private static final Logger logger = LoggerFactory.getLogger(MybatisTestServiceImpl.class);

    @Autowired
    private MybatisTestMapper mapper;

    @Override
    public MybatisTest getMybatisTest(String id) {

        MybatisTest model = mapper.selectByPrimaryKey(id);
        if (model == null) {
            logger.error("[getMybatisTest]delete MybatisTest id={} fail", id);
            throw new RuntimeException("GET data fail");
        }
        return model;

    }

    @Override
    public void save(MybatisTest model) {
        int success = mapper.insertSelective(model);
        if (success <= 0) {
            logger.error("[addMybatisTest]add MybatisTest={} fail",  model.toString());
            throw new RuntimeException("Add data fail");
        }
        return;

    }

    @Override
    public void update(MybatisTest model) {
        int success = mapper.updateByPrimaryKeySelective(model);
        if (success <= 0) {
            logger.error("[updateMybatisTest]update MybatisTest={} fail",  model.toString());
            throw new RuntimeException("Modify data fail");
        }
        return;

    }

    @Override
    public void del(String id) {

        int success = mapper.deleteByPrimaryKey(id);
        if (success <= 0) {
            logger.error("[deleteMybatisTest]delete MybatisTest id={} fail", id);
            throw new RuntimeException("Del data fail");
        }
        return;

    }

    @Override
    public PageInfo<MybatisTest> query(int pageNum, int pageSize, HashMap<String, Object> paramMap) {
        PageHelper.startPage(pageNum, pageSize)

        return new PageInfo<>(mapper.query(paramMap));
    }

    @Override
    public int getCount() {
        return mapper.getCount();
    }

    @Override
    public int queryCount(HashMap<String, Object> paramMap) {
        return mapper.queryCount(paramMap);
    }

}