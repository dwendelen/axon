package axon.ui.commands;

import axon.Application;
import axon.infrastructure.mail.VirtualMailServer;
import axon.infrastructure.mail.virtual.Mail;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.core.annotation.CliOption;
import org.springframework.stereotype.Component;

@Component
public class MailCommands implements CommandMarker {
    @Autowired
    private Application application;

    private VirtualMailServer getVirtualMailServer() {
        return application.getVirtualMailServer();
    }

    @CliCommand("mail list")
    public String list() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Mail mail : getVirtualMailServer().getMails()) {
            String id = String.valueOf(mail.getId());
            stringBuilder.append(StringUtils.leftPad(id, 3));
            stringBuilder.append(":   ");
            stringBuilder.append(mail.getTo());
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    @CliCommand("mail show")
    public String open(
            @CliOption(key = "")
            int id
    ) {
        final Mail mail = getVirtualMailServer().getMailById(id);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("To: ");
        stringBuilder.append(mail.getTo());
        stringBuilder.append("\n\n");

        stringBuilder.append(mail.getMessage());

        return stringBuilder.toString();
    }
}
