package exception.parse;

/**
 * An Exception for when users provided too little arguments for the command.
 *
 * @author Toh Yi Hui A0259080A
 */
public class TooLittleArgumentsException extends ParseException {
    /**
     * Constructor for a TooLittleArgumentsException.
     *
     * @param num the number of arguments expected.
     * @param sample the sample for calling the command.
     */
    public TooLittleArgumentsException(int num, String sample) {
        super("Please call command with " + num + " argument(s).\nFor e.g. " + sample);
    }

    /**
     * Constructor for a TooLittleArgumentsException when no sample command
     * is available.
     *
     * @param num the number of arguments expected.
     */
    public TooLittleArgumentsException(int num) {
        super("Please call command with " + num + " argument(s).");
    }
}
