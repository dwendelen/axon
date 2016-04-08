package axon.ui;

import axon.Application;
import axon.command.RegisterUserCommand;
import axon.command.UpdateEmailAddressCommand;
import org.apache.commons.lang3.StringUtils;

import java.util.Scanner;
import java.util.UUID;

public class Main {
    public static void main(String args[]) {
        Application application = new Application();
        application.init();
        Main main = new Main(application);
        main.run();
    }

    private Application application;
    private Scanner scanner = new Scanner(System.in);

    public Main(Application application) {
        this.application = application;
    }

    private void run() {
        application.init();
        registerUserAndChangeEmail();
    }

    private void registerUserAndChangeEmail() {
        String name = input("Name");
        String email = input("E-mail");

        RegisterUserCommand registerUserCommand = new RegisterUserCommand(name, email);
        UUID uuid = registerUserCommand.getUuid();
        application.send(registerUserCommand);

        String newEmail = input("New e-mail");
        application.send(new UpdateEmailAddressCommand(uuid, newEmail));
    }

    private String input(String prompt) {
        System.out.println(prompt + " ?");
        return scanner.nextLine();
    }

    private void output(String key, Object value) {
        String paddedKey = StringUtils.rightPad(key + ":", 30);
        System.out.println(paddedKey + value.toString());
    }
}
