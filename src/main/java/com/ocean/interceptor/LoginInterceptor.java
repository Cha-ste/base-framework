package com.ocean.interceptor;


import com.alibaba.fastjson.JSON;
import com.ocean.utils.TokenUtils;
import com.ocean.vo.ResultBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getParameter("token");
        //如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        if(token==null||token.equals("")){
            logger.info("用户未登录");
            printJson(response, ResultBean.NO_LOGIN, "用户未登录", "用户未登录");
            return false;
        }

        if (!TokenUtils.validate(token)) {
            printJson(response, ResultBean.NO_LOGIN, "用户未登录", "token非法或已过期");
            return false;
        }
//        User user = (User)getRedisService().get(REDIS_USER_SESSION_KEY+":"+token);
//        if(user==null){
//            printJson(response, "");
//            return false;
//        }
//        String userId = user.getId();
//        User sqlUser = getUserService().getUserById(userId);
//        String enterpriseId =  getUserEnterpriseService().selectEnterpriseByUser(userId);
//        if(enterpriseId==null){
//            getRedisService().set(REDIS_USER_SESSION_KEY+":"+token,sqlUser,SSO_SESSION_EXPIRE);
//            return true;
//        }
//        Enterprise sqlEnterprise = getEnterpriseService().selectEnterpriseById(enterpriseId);
//        getRedisService().set(REDIS_USER_SESSION_KEY+":"+token,sqlUser,SSO_SESSION_EXPIRE);
//        getRedisService().set(REDIS_ENTERPRISE_SESSION_KEY+":"+token,sqlEnterprise,SSO_SESSION_EXPIRE);
        return true;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type, X-Requested-With, token");
        response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, OPTIONS, POST, PUT, DELETE");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Max-Age", "3600");
    }

    private static void printJson(HttpServletResponse response, int code, String message, Object data) {
        ResultBean resultBean = new ResultBean(code,message,data);
        String content = JSON.toJSONString(resultBean);
        printContent(response, content);
    }


    private static void printContent(HttpServletResponse response, String content) {
        try {
            response.reset();
            response.setContentType("application/json");
            response.setHeader("Cache-Control", "no-store");
            response.setCharacterEncoding("UTF-8");
            PrintWriter pw = response.getWriter();
            pw.write(content);
            pw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    private EnterpriseService getEnterpriseService(){
//        return SpringContextHolder.getBean(EnterpriseService.class);
//    }
//    private RedisService getRedisService(){
//        return SpringContextHolder.getBean(RedisService.class);
//    }
//
//    private UserEnterpriseService getUserEnterpriseService(){
//        return SpringContextHolder.getBean(UserEnterpriseService.class);
//    }
//
//    private UserService getUserService(){
//        return SpringContextHolder.getBean(UserService.class);
//    }
}
