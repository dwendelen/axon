package axon.core.infrastructure.logger;

import org.springframework.stereotype.Component;

@Component
public class SystemOutLogger implements Logger {
    private boolean log = false;

    @Override
    public void log(String log) {
        if(this.log) {
            System.out.println(log);
        }
    }

    public void logOn() {
        log = true;
    }

    public void logOff() {
        log = false;
    }
}
