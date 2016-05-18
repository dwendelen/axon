package axon.core;

import axon.core.infrastructure.logger.Logger;
import axon.core.user.event.EmailAddressUpdatedEvent;
import axon.core.user.event.UserRegisteredEvent;
import org.axonframework.eventhandling.scheduling.EventScheduler;
import org.axonframework.eventhandling.scheduling.ScheduleToken;
import org.axonframework.saga.annotation.AbstractAnnotatedSaga;
import org.axonframework.saga.annotation.EndSaga;
import org.axonframework.saga.annotation.SagaEventHandler;
import org.axonframework.saga.annotation.StartSaga;
import org.joda.time.Duration;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

/**
 * This saga counts the number of times you changed your email address
 * within 15 seconds after registration.
 *
 * If you can change your email address 3 times, the saga ends as well.
 */
public class ExampleSaga extends AbstractAnnotatedSaga {
    public static final int TIMEOUT_IN_SEC = 15;

    @Autowired
    private transient EventScheduler eventScheduler;
    @Autowired
    private transient Logger logger;

    private int nbOfChanges = 0;
    private ScheduleToken scheduledTimeout;

    public void setEventScheduler(EventScheduler eventScheduler) {
        this.eventScheduler = eventScheduler;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    @StartSaga
    @SagaEventHandler(associationProperty = "userId")
    public void handle(UserRegisteredEvent userRegisteredEvent) {
        logger.log("Saga started");

        UUID timeoutId = scheduleTimeout();
        associateWith("timeoutId", timeoutId.toString());
    }

    private UUID scheduleTimeout() {
        ExampleSagaTimeoutEvent exampleSagaTimeoutEvent = new ExampleSagaTimeoutEvent();
        scheduledTimeout = eventScheduler.schedule(Duration.standardSeconds(TIMEOUT_IN_SEC), exampleSagaTimeoutEvent);

        logger.log("Countdown starting");
        return exampleSagaTimeoutEvent.getTimeoutId();
    }

    @SagaEventHandler(associationProperty = "userId")
    public void handle(EmailAddressUpdatedEvent emailAddressUpdatedEvent) {
        nbOfChanges++;
        if(nbOfChanges >= 3) {
            eventScheduler.cancelSchedule(scheduledTimeout);
            logger.log("You changed your email address 3 times or more");
            end();
        }
    }

    @SagaEventHandler(associationProperty = "timeoutId")
    @EndSaga
    public void handle(ExampleSagaTimeoutEvent timeoutEvent) {
        logger.log("You changed your email address " + nbOfChanges + " times");
        logger.log("Saga ended");
    }

    private static class ExampleSagaTimeoutEvent {
        private UUID timeoutId = UUID.randomUUID();

        public UUID getTimeoutId() {
            return timeoutId;
        }
    }
}
