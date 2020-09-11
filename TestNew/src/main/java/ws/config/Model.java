package ws.config;

import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

@Component
@Import(MySelect.class)
public class Model {
    public String name;

    public void sys1111(){
        System.out.println(12345);
    }
}
