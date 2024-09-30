package mittens.parser;

import mittens.MittensException;

/**
 * Represents an exception when the user input is invalid.
 */
public class BadInputException extends MittensException {
    private static final String MITTENS_MESSAGE = "Meow?! What does that mean?";
    private static final String HELP_MESSAGE = "Visit izruff.github.io/ip/ to see a list of commands and syntax.";

    public BadInputException(String message) {
        super(message, MITTENS_MESSAGE, HELP_MESSAGE);
    }
}
