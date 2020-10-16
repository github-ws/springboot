package ws.config;


import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class Config {
    @Bean("a")
    public String a() {
        return "a";
    }

    @Bean("b")
    @ConditionalOnBean(name="a")
    public String b() {
        return "b";
    }
}
