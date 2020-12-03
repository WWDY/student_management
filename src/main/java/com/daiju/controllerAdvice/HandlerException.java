package com.daiju.controllerAdvice;

import com.daiju.exception.NoAuthorException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
/**
 * @Author WDY
 * @Date 2020-11-29 16:30
 * @Description TODO
 */
@ControllerAdvice
public class HandlerException {
    @ExceptionHandler(NoAuthorException.class)
    public String noAuthor(){
        return "error/noAuthor";
    }
}
