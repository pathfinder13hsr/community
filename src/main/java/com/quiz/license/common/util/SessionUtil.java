package com.quiz.license.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Component
public class SessionUtil {

    public static String getNickName(){
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        String result = "notUser";
        try{
            result = req.getSession().getAttribute("userNickname").toString();
        }catch (Exception e){
            log.error("SessionUtils Exception. not login.");
        }finally {
            return result;
        }

    }

    public static String getUserType(){
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        String result = "notUser";
        try{
            result =req.getSession().getAttribute("userType").toString();
        }catch (Exception e){
            log.error("SessionUtils Exception. not login.");
        }finally {
            return result;
        }

    }

    public static String getUserId(){
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        String result = "notUser";
        try{
            result = req.getSession().getAttribute("userId").toString();;
        }catch (Exception e){
            log.error("SessionUtils Exception. not login.");
        }finally {
            return result;
        }

    }

    public static Long getSeq(){
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        Long result = 0L;
        try{
//            result = Long.valueOf((String) req.getSession().getAttribute("getSeq"));
//            result = Long.valueOf((String) req.getSession().getAttribute("seq"));
            result = (Long) req.getSession().getAttribute("seq");
        }catch (Exception e){
            log.error("SessionUtils Exception. not login.");
        }finally {
            return result;
        }

    }
}
