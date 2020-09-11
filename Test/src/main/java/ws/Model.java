package ws;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Model  {
    public String name;

    public void sys1111(){
        System.out.println(12345);
    }

   // @PostConstruct
    public void sys(){
        System.out.println("startmodel------t");
    }
}
