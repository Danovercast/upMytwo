package com.dabai.mytwo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class DbException {
    private static Logger logger = LoggerFactory.getLogger(DbException.class);

    /**
     * 400 - Bad Request
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ModelAndView handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        ModelAndView mv = new ModelAndView();
        logger.error("参数解析失败", e);
        mv.addObject("result", "错误请求");
        mv.setViewName("error");
        return mv;
    }


    /**
     * 405 - Method Not Allowed
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ModelAndView handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        logger.error("参数解析失败", e);
        ModelAndView mv = new ModelAndView();
        mv.addObject("result", "参数解析错误");
        mv.setViewName("error");
        return mv;
    }

    /**
     * 415 - Unsupported Media Type
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ModelAndView handleHttpMediaTypeNotSupportedException(Exception e) {
        logger.error("不支持当前媒体类型", e);
        ModelAndView mv = new ModelAndView();
        mv.addObject("result", "支持当前媒体类型");
        mv.setViewName("error");
        return mv;

    }

    /**
     * 500 - Internal Server Error
     */
	/*    @ExceptionHandler(Exception.class)
	    public ModelAndView handleException(Exception e ) {
	   
	        logger.error("服务运行异常", e);
	        ModelAndView mv = new ModelAndView();
	        mv.addObject("result", "嘻嘻。或者参数错误,稍后重试,");
	        mv.setViewName("error");
	        return mv;
	    }*/
    @ExceptionHandler(NumberFormatException.class)
    public ModelAndView handleNumberFormatException(Exception e) {

        logger.error("服务运行异常", e);

        ModelAndView mv = new ModelAndView();
        mv.addObject("result", "警告:错误的参数");
        mv.setViewName("error");
        return mv;
    }
}
