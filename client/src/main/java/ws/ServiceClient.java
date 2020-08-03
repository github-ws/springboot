package ws;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "server",fallback = ServiceFallBack.class)
public interface ServiceClient {
    @RequestMapping("/get")
    public String get();
}
