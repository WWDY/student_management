package com.daiju.aspect;

import com.daiju.annotation.Author;
import com.daiju.exception.NoAuthorException;
import com.daiju.pojo.dto.LoginInfo;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

/**
 * @Author WDY
 * @Date 2020-11-29 13:53
 * @Description TODO
 */
@Component
@Aspect
public class AuthorAspect {

    @Autowired
    HttpSession session;

    @Pointcut("@annotation(com.daiju.annotation.Author)")
    public void pointCut(){}

    @Before(value = "pointCut()")
    public Object authorUser(JoinPoint joinPoint) throws Throwable{
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Author methodAnnotation = signature.getMethod().getAnnotation(Author.class);
        if(methodAnnotation==null){
            return true;
        }else {
            if(!methodAnnotation.value()){
                return true;
            }else {
                if(authorization()){
                    return true;
                }else {
                    throw new NoAuthorException("无权访问");
                }
            }
        }

    }

    public boolean authorization(){
        LoginInfo user = (LoginInfo)session.getAttribute("user");

        if (user.getUsername().length()>4) {
            return false;
        }else {
            return true;
        }
    }
}
