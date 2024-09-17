package gopher.exception;

/**
 * Thrown if the given command is not recognized.
 */
public class UnknownCommandException extends Exception {
    private String command;

    public UnknownCommandException(String command) {
        this.command = command;
    }

    @Override
    public String getMessage() {
        return "Sorry, but I can't recognize this command: "
                + this.command
                + "\nPlease try again...\n\n";
    }
}
