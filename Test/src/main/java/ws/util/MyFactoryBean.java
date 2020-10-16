package ws.util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

import java.util.Arrays;


@Component
public class MyFactoryBean implements FactoryBean {


    @Override
    public Object getObject() throws Exception {
        return "me me me";
    }

    @Override
    public Class<?> getObjectType() {
        return String.class;
    }
}
