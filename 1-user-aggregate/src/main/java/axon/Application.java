package axon;

import axon.command.CreateUserCommand;
import axon.hello.HelloCommand;
import axon.hello.HelloCommandHandler;
import org.apache.commons.lang3.StringUtils;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.SimpleCommandBus;
import org.axonframework.commandhandling.annotation.AggregateAnnotationCommandHandler;
import org.axonframework.commandhandling.annotation.AnnotationCommandHandlerAdapter;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.commandhandling.gateway.DefaultCommandGateway;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.SimpleEventBus;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventstore.EventStore;
import org.axonframework.eventstore.fs.FileSystemEventStore;
import org.axonframework.eventstore.fs.SimpleEventFileResolver;
import org.axonframework.repository.Repository;
import org.axonframework.unitofwork.DefaultUnitOfWork;
import org.axonframework.unitofwork.UnitOfWork;

import java.io.File;
import java.util.Scanner;
import java.util.UUID;

public class Application {
    private CommandGateway commandGateway;
    private Repository<User> userRepository;

    public static void main(String args[]) {
        Application application = new Application();
        application.initAxon();

        application.helloWold();
        application.createUser();
    }

    private void helloWold() {
        commandGateway.send(new HelloCommand("world!"));
    }

    private void createUser() {
        String name = input("Name");
        String email = input("E-mail");

        //TODO Include email
        CreateUserCommand createUserCommand = new CreateUserCommand(name);
        output("Creating user with UUID", createUserCommand.getUuid());

        UUID uuid = commandGateway.sendAndWait(createUserCommand);
        output("Command returned UUID", uuid);

        UnitOfWork unitOfWork = DefaultUnitOfWork.startAndGet();
        User user = userRepository.load(uuid);
        unitOfWork.commit();

        output("User in repo has UUID", user.getUuid());
        output("User in repo has Identifier", user.getIdentifier());
        output("User in repo has Version", user.getVersion());
    }

    public void initAxon() {
        CommandBus commandBus = new SimpleCommandBus();

        EventBus eventBus = new SimpleEventBus();

        EventStore eventStore = new FileSystemEventStore(new SimpleEventFileResolver(new File("events")));
        EventSourcingRepository<User> eventSourcedUserRepository = new EventSourcingRepository<>(User.class, eventStore);
        eventSourcedUserRepository.setEventBus(eventBus);

        AggregateAnnotationCommandHandler.subscribe(User.class, eventSourcedUserRepository, commandBus);
        AnnotationCommandHandlerAdapter.subscribe(new HelloCommandHandler(), commandBus);

        this.commandGateway = new DefaultCommandGateway(commandBus);
        this.userRepository = eventSourcedUserRepository;
    }

    private Scanner scanner = new Scanner(System.in);

    private String input(String prompt) {
        System.out.println(prompt + " ?");
        return scanner.nextLine();
    }

    private void output(String key, Object value) {
        String paddedKey = StringUtils.rightPad(key + ":", 30);
        System.out.println(paddedKey + value.toString());
    }
}
