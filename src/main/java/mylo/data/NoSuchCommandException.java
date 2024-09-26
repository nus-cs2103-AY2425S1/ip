package mylo.data;

/**
 * Exception thrown when a user inputs a command that does not exist.
 * <p></p>
 * <p>This exception is used to indicate that the input provided by the user
 * does not match any valid command.</p>
 *
 * @author cweijin
 */
public class NoSuchCommandException extends Exception {

    /**
     * Constructs a {@code NoSuchCommandException} with a message indicating the invalid command.
     *
     * @param input The invalid command input by the user.
     */
    public NoSuchCommandException(String input) {
        super(String.format("%s is not a valid command. Please try again with: "
                + "\ntodo \nevent \ndeadline \nlist \nbye", input));
    }

    @Override
    public String toString() {
        return super.getMessage();
    }
}
