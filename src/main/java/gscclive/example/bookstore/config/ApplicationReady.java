package gscclive.example.bookstore.config;

import org.springframework.boot.context.event.SpringApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ApplicationReady {

    @EventListener(SpringApplicationEvent.class)
    public void readyEvent() {
        log.info("Bookstore service ready.");
    }
}
