package ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.*;
import ws.proxy.MyProxyInterface;


@Configuration
@ComponentScan("ws")
@EnableAspectJAutoProxy
public class config {
}
