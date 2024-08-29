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
     * Creates an empty Command.
     */
    public Command() {
        // empty
    }

    /**
     * Creates a Command with a given Message.
     *
     * @param message The message in the Command.
     */
    public Command(String message) {
        setMessage(message);
    }

    /**
     * Creates a Command with a given Message array.
     *
     * @param message The messages in the Command.
     */
    public Command(String[] message) {
        setMessage(message);
    }

    /**
     * Executes a function when called to process information.
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
