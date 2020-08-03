package ws;

import org.springframework.stereotype.Component;

@Component
public class ServiceFallBack implements ServiceClient {
    @Override
    public String get() {
        return "liangliang";
    }
}
