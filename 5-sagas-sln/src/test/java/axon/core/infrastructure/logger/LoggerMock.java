package axon.core.infrastructure.logger;

import static org.assertj.core.api.Assertions.assertThat;

public class LoggerMock implements Logger {
    private String log = "";

    @Override
    public void log(String log) {
        this.log += log + "\n";
    }

    public void assertLog(String expectedLog) {
        assertThat(this.log).isEqualTo(expectedLog);
    }
}