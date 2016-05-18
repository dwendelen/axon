package axon.core;

import axon.core.infrastructure.logger.LoggerMock;
import axon.core.user.event.EmailAddressUpdatedEvent;
import axon.core.user.event.UserRegisteredEvent;
import org.axonframework.test.saga.AnnotatedSagaTestFixture;
import org.joda.time.Duration;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

public class ExampleSagaTest {
    public static final UUID USER_ID = UUID.randomUUID();
    public static final String NAME = "Username";
    public static final String EMAIL_0 = "email0";
    public static final String EMAIL_1 = "email1";
    public static final String EMAIL_2 = "email2";
    public static final String EMAIL_3 = "email3";

    private AnnotatedSagaTestFixture fixture;
    private LoggerMock loggerMock = new LoggerMock();

    @Before
    public void setup() {
        fixture = new AnnotatedSagaTestFixture(ExampleSaga.class);
        fixture.registerResource(loggerMock);
    }

    @Test
    public void theSagaDoesNotEndAfterTwoEmailUpdates() throws Exception {
        fixture.givenAggregate(USER_ID)
                .published(
                        new UserRegisteredEvent(USER_ID, NAME, EMAIL_0),
                        new EmailAddressUpdatedEvent(USER_ID, EMAIL_1))
                .whenPublishingA(new EmailAddressUpdatedEvent(USER_ID, EMAIL_2))
                .expectActiveSagas(1);

        loggerMock.assertLog(
                "Saga started\n"+
                "Countdown starting\n"
        );
    }

    @Test
    public void theSageEndsAfterThreeEmailUpdates() throws Exception {
        fixture.givenAggregate(USER_ID)
                .published(
                        new UserRegisteredEvent(USER_ID, NAME, EMAIL_0),
                        new EmailAddressUpdatedEvent(USER_ID, EMAIL_1),
                        new EmailAddressUpdatedEvent(USER_ID, EMAIL_2))
                .whenPublishingA(new EmailAddressUpdatedEvent(USER_ID, EMAIL_3))
                .expectActiveSagas(0);

        loggerMock.assertLog(
                "Saga started\n" +
                "Countdown starting\n" +
                "You changed your email address 3 times or more\n");
    }

    @Test
    public void theSageEndsAfterTheTimeout() throws Exception {
        fixture.givenAggregate(USER_ID)
                .published(
                        new UserRegisteredEvent(USER_ID, NAME, EMAIL_0),
                        new EmailAddressUpdatedEvent(USER_ID, EMAIL_1),
                        new EmailAddressUpdatedEvent(USER_ID, EMAIL_2))
                .whenTimeElapses(Duration.standardDays(ExampleSaga.TIMEOUT_IN_SEC))
                .expectActiveSagas(0);

        loggerMock.assertLog(
                "Saga started\n" +
                "Countdown starting\n" +
                "You changed your email address 2 times\n" +
                "Saga ended\n");
    }
}