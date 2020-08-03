package ws;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class a{

    @Autowired
    Model m;

    @RequestMapping("/a")
    public String  hello(String name){
        System.out.println("helloa"+name);
        return "a" ;
    }
}
