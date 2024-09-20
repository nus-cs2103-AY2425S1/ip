package ai.exception;

/**
 * Creates an exception for incorrect input. Provides users with explanation of what went wrong and suggestions.
 */
public class WrongFormatAiException extends AiException {
    /**
     * Creates an exception that is in the format of {command_type} {suggestions}.
     * @param description the description of what went wrong with user's command.
     * @param suggestion the correct format users should input.
     */
    public WrongFormatAiException(String description, String suggestion) {
        super(String.format("Whoopsies... %s\n\nTry something like\n\n\"%s\"",
                description, suggestion));
    }
}
