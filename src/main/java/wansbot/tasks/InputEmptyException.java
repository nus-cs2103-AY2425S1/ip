package wansbot.tasks;

/**
 * Exception when input after the command is empty.
 */
public class InputEmptyException extends RuntimeException {

    /**
     * Prints to console when user inputs nothing after the command.
     */
    public InputEmptyException(String userInput) {
        super("You need to input something after " + userInput);
    }

    /**
     * Prints to console when a specific input is missing from command.
     *
     * @param userInput Command input by user.
     * @param missingInfo Depending on context when InputEmptyException is invoked, prints to console what parts
     *     of the command are missing.
     */
    public InputEmptyException(String userInput, String missingInfo) {
        super("You need to include " + missingInfo + " in your command!");
    }
}
