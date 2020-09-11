package ws;

import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ws.config.Config;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class TestNew implements ApplicationContextAware {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(TestNew.class);
        System.out.println(applicationContext.getBean(Config.class));
    }

    public static void main1(String[] args) throws Exception {
        AnnotationConfigApplicationContext a=new AnnotationConfigApplicationContext();
        a.register(Config.class);
        a.refresh();
        a.getBean("model");
    }

    public static void test(String[] args) {
        List l=new ArrayList<>();
        l.add("a");
        l.add("22");
        l.add("2");
        for (Object o :l){
            if(o.toString().equals("22")){
                return;
            }
            System.out.println(o.toString());
        }
        System.out.println("end");
        return;
    }

    static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }
}
