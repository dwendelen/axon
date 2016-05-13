package axon.core;

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

    private int nbOfChanges = 0;
    private ScheduleToken scheduledTimeout;

    @StartSaga
    @SagaEventHandler(associationProperty = "userId")
    public void handle(UserRegisteredEvent userRegisteredEvent) {
        System.out.println("Saga started");

        UUID timeoutId = scheduleTimeout();
        associateWith("timeoutId", timeoutId.toString());
    }

    private UUID scheduleTimeout() {
        ExampleSagaTimeoutEvent exampleSagaTimeoutEvent = new ExampleSagaTimeoutEvent();
        scheduledTimeout = eventScheduler.schedule(Duration.standardSeconds(TIMEOUT_IN_SEC), exampleSagaTimeoutEvent);

        System.out.println("Countdown starting");
        return exampleSagaTimeoutEvent.getTimeoutId();
    }

    @SagaEventHandler(associationProperty = "userId")
    public void handle(EmailAddressUpdatedEvent emailAddressUpdatedEvent) {
        nbOfChanges++;
        if(nbOfChanges >= 3) {
            eventScheduler.cancelSchedule(scheduledTimeout);
            System.out.println("You changed your email address 3 times or more");
            end();
        }
    }

    @SagaEventHandler(associationProperty = "timeoutId")
    @EndSaga
    public void handle(ExampleSagaTimeoutEvent timeoutEvent) {
        System.out.println("You changed your email address " + nbOfChanges + " times");
        System.out.println("Saga ended");
    }

    private static class ExampleSagaTimeoutEvent {
        private UUID timeoutId = UUID.randomUUID();

        public UUID getTimeoutId() {
            return timeoutId;
        }
    }
}
