package ai.exception;

/**
 * Creates exception when command has empty arguments.
 */
public class EmptyArgumentAiException extends AiException {
    /**
     * Creates an exception that is in the format of {command_type} {suggestions}.
     * @param command the command type, (e.g.) todo, event, deadline, due.
     * @param suggestion the correct format users should input.
     */
    public EmptyArgumentAiException(String command, String suggestion) {
        super(String.format("Whoopsies... %s cannot be empty >.< \n\nTry something like\n\n\"%s\"",
                command, suggestion));
    }
}
