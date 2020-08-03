package ws.util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
//@Component
public class MyAop {

    @Pointcut("execution(public * ws.Model.*(..))")
    public void poinCut(){}

    @Before("poinCut()")
    public void logStart(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        System.out.println(joinPoint.getSignature().getName()+" 运行。。。@Before "+ Arrays.asList(args));
    }

    @After("poinCut()")
    public void  logEnd(){
        System.out.println("结束@After");
    }
}
