package duck.exceptions;

import java.util.List;

/**
 * Class representing errors that occur when there are missing arguments in
 * commands.
 */
public class MissingArgsException extends Exception {
    private List<String> missingArgs;
    private UsageException usageException;

    /**
     * Constructor for MissingArgsException.
     *
     * @param missingArgs    List of missing arguments.
     * @param usageException The cause of this exception.
     */
    public MissingArgsException(List<String> missingArgs, UsageException usageException) {
        this.missingArgs = missingArgs;
        this.usageException = usageException;
    }

    @Override
    public String toString() {
        return String.format("%s\nMissing args: %s", usageException.toString(), String.join(", ", missingArgs));
    }
}
