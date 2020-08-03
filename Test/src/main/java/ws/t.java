package ws;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import javax.annotation.PostConstruct;

//@SpringBootApplication
public class t {
    public static void main(String[] args) {
        //SpringApplication.run(t.class);
        AnnotationConfigApplicationContext a=new AnnotationConfigApplicationContext();
        a.register(config.class);
        a.refresh();
        a.getBean("");
    }

}
