package com.ocean.exception;

import com.ocean.vo.ResultBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.zip.DataFormatException;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * http请求的方法不正确
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public ResultBean<String> httpRequestMethodNotSupportedExceptionHandler(HttpRequestMethodNotSupportedException e) {
        logger.error("http请求的方法不正确:【" + e.getMessage() + "】");
        return new ResultBean<>(ResultBean.NOT_ALLOWED, "http请求的方法不正确", e.getMessage());
    }

    /**
     * 请求参数不全
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    public ResultBean<String> missingServletRequestParameterExceptionHandler(MissingServletRequestParameterException e) {
        logger.error("请求参数不全:【" + e.getMessage() + "】");
        return new ResultBean<>(ResultBean.BAD_REQUEST, "请求参数不全", e.getMessage());
    }

    /**
     * 请求参数类型不正确
     */
    @ExceptionHandler(TypeMismatchException.class)
    @ResponseBody
    public ResultBean<String> typeMismatchExceptionHandler(TypeMismatchException e) {
        logger.error("请求参数类型不正确:【" + e.getMessage() + "】");
        return new ResultBean<>(ResultBean.BAD_REQUEST, "请求参数类型不正确", e.getMessage());
    }

    /**
     * 数据格式不正确
     */
    @ExceptionHandler(DataFormatException.class)
    @ResponseBody
    public ResultBean<String> dataFormatExceptionHandler(DataFormatException e) {
        logger.error("数据格式不正确:【" + e.getMessage() + "】");
        return new ResultBean<>(ResultBean.FORBIDDEN, "数据格式不正确", e.getMessage());
    }

    /**
     * 非法输入
     */
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public ResultBean<String> illegalArgumentExceptionHandler(IllegalArgumentException e) {
        logger.error("非法输入:【" + e.getMessage() + "】");
        return new ResultBean<>(ResultBean.ERROR, "非法输入", e.getMessage());
    }

    /**
     * 其他异常
     */
    @ExceptionHandler  //处理其他异常
    @ResponseBody
    public ResultBean<String> allExceptionHandler(Exception e) {
        logger.error("具体错误信息:【" + e.getMessage() + "】");
        int count = 0; //只打印15行的错误堆栈
        for (StackTraceElement stackTraceElement : e.getStackTrace()) {
            logger.error(stackTraceElement.toString());
            if (count++ > 13) break;
        }
        return new ResultBean<>(ResultBean.ERROR, "服务器错误", e.getMessage());
    }

    /**
     * 空指针异常
     */
    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public ResultBean<String> nullPointerExceptionHandler(NullPointerException e) {
        return new ResultBean<>(ResultBean.ERROR, "空指针异常", e.getMessage());
    }

    /**
     * 类型转换异常
     */
    @ExceptionHandler(ClassCastException.class)
    @ResponseBody
    public ResultBean<String> classCastExceptionHandler(NullPointerException e) {
        return new ResultBean<>(ResultBean.ERROR, "空指针异常", e.getMessage());
    }

}
