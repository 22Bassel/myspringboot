package com.first.project.aspect;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.first.project.services.jwt;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class logging {


    Logger log= LoggerFactory.getLogger(logging.class);

    @Autowired
    private jwt jwt;


    @Pointcut(value = " execution(* com.first.project.controllers.main_controller.*(..) )")
    public void Mypointcut()
    {


    }




    @Around("Mypointcut()")
    public Object applicationlogger(ProceedingJoinPoint pjp) throws Throwable {

        HttpServletRequest request =((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        String authheader=null ;


        Cookie[] cookies =request.getCookies();
        if(cookies != null ){
            for(Cookie c : cookies){
                if(c.getName().equals("Authorization")){
                    authheader="Bearer "+ c.getValue();
                }
            }
        }

        String username = null;
        String jwtstring = null;

        if (authheader != null && authheader.startsWith("Bearer ")) {
            jwtstring = authheader.substring(7);
            username = jwt.extractusername(jwtstring);
        }



            ObjectMapper mapper = new ObjectMapper();


        String MethodName = pjp.getSignature().getName();

        String ClassName = pjp.getTarget().getClass().toString();

        Object[] array = pjp.getArgs();

        log.info("user : "+username+" method invoke " + ClassName + " : " + MethodName + "()" + "arguments:" + mapper.writeValueAsString(array));

        Object object = pjp.proceed();

        log.info("user : "+username+" class :"+ClassName + " : " + MethodName + "()" + "Response : " + mapper.writeValueAsString(object));

        return object;
    }
    }
