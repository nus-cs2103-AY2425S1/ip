package oyster.commands;

public abstract class Command {
    public enum Type {
        BYE,
        ERROR,
        LIST,
        TODO,
    }
    private String[] message;

    public Command() {
        // empty
    }

    public Command(String message) {
        setMessage(message);
    }

    public Command(String[] message) {
        setMessage(message);
    }

    public abstract void execute();

    protected void setMessage(String message) {
        this.message = new String[] {message};
    }

    protected void setMessage(String[] message) {
        this.message = message;
    }

    public String[] getMessage() {
        return message;
    }
}
