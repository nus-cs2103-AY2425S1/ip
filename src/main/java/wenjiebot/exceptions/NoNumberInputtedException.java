package wenjiebot.exceptions;

/**
 * Represents an exception that is thrown when the user does not provide a number
 * after a mark, unmark, or delete command. This exception provides a custom error
 * message to remind the user to include a number with these commands.
 */
public class NoNumberInputtedException extends WenJieException {

    /**
     * Constructs a NoNumberInputtedException with a default error message.
     */
    public NoNumberInputtedException() {
        super("test");
    }

    /**
     * Returns a custom error message that instructs the user to include a number
     * after the mark, unmark, or delete command.
     *
     * @return the custom error message string
     */
    @Override
    public String getMessage() {
        return "wah shag bro, you forgot to add a number after your mark/unmark/delete command";
    }
}
