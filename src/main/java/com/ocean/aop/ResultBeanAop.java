package com.ocean.aop;

import com.ocean.controller.UserController;
import com.ocean.utils.TokenUtils;
import com.ocean.vo.ResultBean;
import org.apache.ibatis.type.TypeException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.mybatis.spring.MyBatisSystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ResultBeanAop {
    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Pointcut("execution(public com.ocean.vo.ResultBean *(..))")
    public void performance() {}

    @Around("performance()")
    public Object handlerControllerMethod (ProceedingJoinPoint pjp) {
        long startTime = System.currentTimeMillis();
        ResultBean<?> resultBean;
        try {
            resultBean = (ResultBean<?>) pjp.proceed();
            //add token to resultBean
            resultBean.setToken(TokenUtils.genToken());
            logger.info(pjp.getSignature() + ", elapsed time: " + (System.currentTimeMillis() - startTime) + "ms");
        } catch (Throwable e) {
            resultBean = handlerException(pjp, e);
        }

        return resultBean;
    }

    private ResultBean<?> handlerException(ProceedingJoinPoint pjp, Throwable e) {
        ResultBean<?> resultBean = new ResultBean<>();

        if (e instanceof TypeException || e instanceof MyBatisSystemException) {
            resultBean.setMessage(e.getLocalizedMessage());
            resultBean.setCode(ResultBean.ERROR);
        } else {
            resultBean.setMessage(e.toString());
            resultBean.setCode(ResultBean.ERROR);
        }

//        resultBean.setToken(TokenUtils.getToken());
        logger.error("=================== Exception start ===================");
        logger.error(pjp.getSignature() + ", Exception message: ", e);
        logger.error("=================== Exception end ===================");

        return resultBean;
    }
}
