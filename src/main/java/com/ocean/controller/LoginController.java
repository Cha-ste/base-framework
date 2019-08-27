package com.ocean.controller;

import com.ocean.entity.User;
import com.ocean.service.UserService;
import com.ocean.vo.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class LoginController {
    @Autowired
    UserService userService;

    /**
     * 用户登录
     *
     * @param userName 用户名
     * @param password 用户密码
     * @return resultBean
     */
    @GetMapping("login")
    public ResultBean<String> login (String userName, String password) {
        ResultBean<String> resultBean = new ResultBean<>();

        User user = userService.getUser(userName);
        if (user == null) {
            resultBean.setCode(ResultBean.NOT_ALLOWED);
            resultBean.setMessage("用户不存在");
            return resultBean;
        }
        if (!password.equals(user.getPassword())) {
            resultBean.setCode(ResultBean.NOT_ALLOWED);
            resultBean.setMessage("密码错误");
            return resultBean;
        }

        resultBean.setCode(ResultBean.SUCCESS);
        resultBean.setMessage("登录成功");

        return resultBean;
    }
}
