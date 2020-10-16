package ws;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ws.mapper.Base;
import ws.mapper1.Base1;
import ws.util.MyFactoryBean;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@Controller
public class a{

    @Autowired
    Model m;

    @Autowired
    MyFactoryBean my;

    @Resource
    String myFactoryBean;
   // @Value("${server.port}")
    int port;

    @RequestMapping("/a")
    public String  hello(String name){
        System.out.println(port+"---------/a"+"--"+name);
        return "live2d/demo";
    }

    @Autowired
    public Base base;

    @Autowired
    public Base1 base1;
    @RequestMapping("/b")
    @ResponseBody
    public Object  b(String name, HttpServletResponse response){
        System.out.println(my.getClass());
        System.out.println(my.getClass().toString());
        System.out.println(myFactoryBean.getClass());
        System.out.println(myFactoryBean.getClass().toString());
        System.out.println("into----b");
        response.setContentType("text/html;charset=utf-8");
        base1.test();
        return base.test();
    }

}
