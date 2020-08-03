package ws.util;

import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

public class MyApplicationEvent extends ApplicationEvent {

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public MyApplicationEvent(Object source) {
        super(source);
    }

}
