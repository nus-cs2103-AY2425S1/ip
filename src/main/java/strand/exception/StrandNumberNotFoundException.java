package strand.exception;

/**
 * Thrown when a required number is missing in a command that requires it.
 */
public class StrandNumberNotFoundException extends StrandException {
    protected String command;

    public StrandNumberNotFoundException(String command) {
        this.command = command;
    }

    @Override
    public String getMessage() {
        return "Index for task not found " + super.getMessage()
                + "Please input a number after " + this.command
                + ". (e.g. " + this.command + " 1)";
    }
}
