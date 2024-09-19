package exception;

/**
 * The MissingArgumentException class is used to indicate missing arguments
 */
public class MissingArgumentException extends LevelHundredException {
    private String command;
    private String arg;

    /**
     * Constructor for MissingArgumentException
     * @param command String representing the user command
     * @param arg String representing the missing argument
     */
    public MissingArgumentException(String command, String arg) {
        super("Missing inputs for command");
        this.command = command;
        this.arg = arg;
    }

    @Override
    public String toString() {
        return super.toString() + " " + this.command + ": " + this.arg;
    }
}
