package pe.edu.tecsup.lms.analytics.application.eventhandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import pe.edu.tecsup.lms.courses.domain.event.CourseCreatedEvent;
import pe.edu.tecsup.lms.courses.domain.event.CoursePublishedEvent;

/**
 * Es el consumidor de eventos
 */
@Slf4j
@Component
public class AnalyticsEventHandler {

    @Async("eventExecutor")
    @EventListener
    public void handleCourseCreated(CourseCreatedEvent event) throws InterruptedException {

        log.info("Starting analytics ........ : {}", event);
        Thread.sleep(4000);
        log.info("Ending analytics ........ : {}", event.getTitle());

    }

    @Async("eventExecutor")
    @EventListener
    public void handleCoursePublished(CoursePublishedEvent event) throws InterruptedException {

        log.info("Starting analytics ........ : {}", event);

    }


}
