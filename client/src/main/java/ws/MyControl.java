package ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MyControl {
    String service="http://server/";
    @Autowired
    RestTemplate r;

    @Autowired
    ServiceClient c;

    @RequestMapping("/get")
    public String a(){
        System.out.println("into");
     //  String s= r.getForObject(service+"get",String.class);
        String s= c.get();
        System.out.println("fanhiu"+s);
        return "client";
    }
}
