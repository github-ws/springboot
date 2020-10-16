package ws.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class Model {
    public String name;

    @RequestMapping("/a")
    @ResponseBody
    public List a(@RequestBody String a){
        JSONObject jsonObject = JSON.parseObject(a);
        JSONArray a1 = jsonObject.getJSONArray("a");
        System.out.println("into a ........"+a);
        List l=new ArrayList();
        l.add("1");
        return l;
    }

    @RequestMapping("/b")
    @ResponseBody
    public List b(String a){
        System.out.println("into b ........"+a);
        List l=new ArrayList();
        l.add("2");
        return l;
    }

    @RequestMapping("/c")
    @ResponseBody
    public List c(@RequestBody Map a, @RequestParam(value = "a",required = false) String b){
        System.out.println("into c ........"+a);
        List l=new ArrayList();
        l.add("3");
        return l;
    }

    @RequestMapping("/r")
    public String r(){
        System.out.println("into r ........");

        return "redirect:https://www.baidu.com";
    }
}
