package exception.parse;

/**
 * An Exception for when users provided too many arguments for the command.
 *
 * @author Toh Yi Hui A0259080A
 */
public class TooManyArgumentsException extends ParseException {
    /**
     * Constructor for a TooManyArgumentsException.
     *
     * @param num the number of arguments expected.
     * @param sample the sample for calling the command.
     */
    public TooManyArgumentsException(int num, String sample) {
        super("Please call command with " + num + " argument(s).\nFor e.g. " + sample);
    }

    /**
     * Constructor for a TooManyArgumentsException when no sample command
     * is available.
     *
     * @param num the number of arguments expected.
     */
    public TooManyArgumentsException(int num) {
        super("Please call command with " + num + " argument(s).");
    }
}
