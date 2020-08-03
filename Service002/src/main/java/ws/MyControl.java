package ws;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyControl {

    @RequestMapping("/get")
    public String a(){
        System.out.println("into");
        return "service002";
    }
}
