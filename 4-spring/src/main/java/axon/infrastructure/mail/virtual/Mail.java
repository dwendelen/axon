package axon.infrastructure.mail.virtual;

public class Mail {
    private int id;
    private String to;
    private String message;

    public Mail(int id, String to, String message) {
        this.id = id;
        this.to = to;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public String getTo() {
        return to;
    }

    public String getMessage() {
        return message;
    }
}
