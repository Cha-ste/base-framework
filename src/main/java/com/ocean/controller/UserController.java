package com.ocean.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import com.ocean.entity.User;
import com.ocean.service.UserService;
import com.ocean.vo.ResultBean;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import com.github.pagehelper.PageInfo;

@RestController("UserController")
@RequestMapping("/User")
public class UserController {

    public static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService service;

    @GetMapping(value = "/get", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultBean<User> get(@RequestParam String id) {
        ResultBean<User> resultBean = new ResultBean<>();
        try {
            User entity=service.getUser(id);
            resultBean.setCode(ResultBean.SUCCESS);
            resultBean.setData(entity);
        } catch (Exception e) {
            resultBean.setCode(ResultBean.ERROR);
            resultBean.setMessage("Fail:" + e.getMessage());
            logger.error("Fail:", e);
            return resultBean;
        }
        resultBean.setCode(ResultBean.SUCCESS);
        resultBean.setMessage("SUCCESS");
        return resultBean;
    }

    @GetMapping(value = "query/{pageNum}/{pageSize}")
    @ResponseBody
    @SuppressWarnings("unchecked")
    public ResultBean<PageInfo<User>> query(
        @PathVariable int pageNum,
        @PathVariable int pageSize,
        @RequestParam(required = false) String param) {
        logger.info("pageNum:{},pageSize:{},param:{}", pageNum, pageSize, param);
        ResultBean<PageInfo<User>> resultBean = new ResultBean<>();
        HashMap<String, Object> paramMap = new HashMap<>();
        if (StringUtils.isEmpty(param)) {
            try {
                paramMap = new ObjectMapper().readValue(param, HashMap.class);
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }
        }else{
            paramMap.put("orderBy","id desc");
        }
        PageInfo<User> pageInfo = service.query(pageNum, pageSize, paramMap);
        int count = service.getCount();
        resultBean.setCode(ResultBean.SUCCESS);
        resultBean.setData(pageInfo);
        return resultBean;
    }

    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultBean save(@RequestBody User model) {
        ResultBean resultBean = new ResultBean();
        try {
            User record = new User();
            BeanUtils.copyProperties(model, record);

            if (record.getId() == null) {
                //TODO 设置主键
                service.save(record);
            } else {
                service.update(record);
            }

        } catch (Exception e) {
            resultBean.setData(ResultBean.ERROR);
            resultBean.setMessage("Fail:" + e.getMessage());
            logger.error("Fail:", e);
            return resultBean;
        }
        resultBean.setData(ResultBean.SUCCESS);
        resultBean.setMessage("SUCCESS");
        return resultBean;
    }

    @DeleteMapping(value = "/del", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultBean del(String id) {
        ResultBean resultBean = new ResultBean();
        try {
            service.del(id);
        } catch (Exception e) {
            resultBean.setCode(ResultBean.ERROR);
            resultBean.setMessage("Fail:" + e.getMessage());
            logger.error("Fail:", e);
            return resultBean;
        }
        resultBean.setCode(ResultBean.SUCCESS);
        resultBean.setMessage("SUCCESS");
        return resultBean;
    }

}