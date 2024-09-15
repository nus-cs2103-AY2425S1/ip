package elysia.exceptions;

public class EmptyArgumentException extends ElysiaException {

    private String details;

    /**
     * Constructs a new ElysiaException with the specified detail message.
     *
     * @param command The type of command to be added to the error message.
     */
    public EmptyArgumentException(String command) {
        super("No argument found for " + command + " command. 1 expected");
        this.details = command;
    }


    public String getDetails() {
        return details;
    }
}
