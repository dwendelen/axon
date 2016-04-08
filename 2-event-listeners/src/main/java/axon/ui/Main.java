package axon.ui;

import axon.Application;
import axon.User;
import axon.command.RegisterUserCommand;
import org.apache.commons.lang3.StringUtils;
import org.axonframework.unitofwork.DefaultUnitOfWork;
import org.axonframework.unitofwork.UnitOfWork;

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
        registerUser();
    }

    private void registerUser() {
        String name = input("Name");
        String email = input("E-mail");

        RegisterUserCommand registerUserCommand = new RegisterUserCommand(name, email);
        output("Creating user with UUID", registerUserCommand.getUuid());

        UUID uuid = application.send(registerUserCommand);
        output("Command returned", uuid);

        User user = application.getUser(uuid);

        output("User in repo has UUID", user.getUuid());
        output("User in repo has Identifier", user.getIdentifier());
        output("User in repo has Version", user.getVersion());
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
