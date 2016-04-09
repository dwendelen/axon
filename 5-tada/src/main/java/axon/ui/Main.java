package axon.ui;

import org.springframework.shell.Bootstrap;

public class Main {
    public static void main(String args[]) throws Exception {
        Bootstrap.main(new String[0]);
        try {
            while (true) {
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
