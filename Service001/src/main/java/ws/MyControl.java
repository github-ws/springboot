package ws;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyControl {

    @RequestMapping("/get")
    public String a(String name) throws Exception {
        System.out.println("into");
        if (name==null)
            throw new Exception();
        return "service001";
    }
}
