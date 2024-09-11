package ipman.parser;

/**
 * Thrown when there are insufficient arguments for a particular command to
 * execute.
 */
public class MissingArgumentException extends RuntimeException {
    /**
     * Constructs a <code>MissingArgumentException</code> with an error message
     * showing the difference in expected and actual argument count.
     *
     * @param expectedNoArgs expected number of arguments for the command to
     *                       execute
     * @param actualNoArgs actual number of arguments found for the command
     */
    public MissingArgumentException(int expectedNoArgs, int actualNoArgs) {
        super(String.format(
            "Missing arguments for keyword. Expected %d but found %d arguments",
            expectedNoArgs,
            actualNoArgs
        ));
    }
}
