package yihuibot.exception.parse;

/**
 * An Exception for when user's arguments is not the correct type
 * or format.
 *
 * @author Toh Yi Hui A0259080A
 */
public class InvalidArgumentException extends ParseException {
    /**
     * Constructor for an InvalidArgumentException. Informs the
     * user of what the expected argument should be like.
     *
     * @param expected the expected type or format of argument.
     */
    public InvalidArgumentException(String expected) {
        super("Invalid argument. Expected " + expected);
    }

    /**
     * Constructor for an InvalidArgumentException. Informs the
     * user of what the expected argument and what they input.
     *
     * @param expected the expected type or format of argument.
     * @param input the input of the user.
     */
    public InvalidArgumentException(String expected, String input) {
        super("Invalid argument. Expected " + expected + " but given " + input);
    }
}
