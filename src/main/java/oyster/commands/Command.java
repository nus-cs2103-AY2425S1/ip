package oyster.commands;

public abstract class Command {
    public enum Type {
        BYE,
        ERROR,
        LIST,
        TODO,
    }
    private String[] message;

    /**
     * Creates an empty Command
     */
    public Command() {
        // empty
    }

    /**
     * @param message The message in the Command
     */
    public Command(String message) {
        setMessage(message);
    }

    /**
     * @param message The messages in the Command
     */
    public Command(String[] message) {
        setMessage(message);
    }

    /**
     * Executes a function when called to process information
     */
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
