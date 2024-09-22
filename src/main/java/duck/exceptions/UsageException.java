package duck.exceptions;

/**
 * Class representing errors encountered in {@code Command} classes
 */
public class UsageException extends Exception {
    private final String exampleUsage;
    private final String[] requiredArgs;

    /**
     * Constructor for UsageException
     *
     * @param exampleUsage Description of example usage.
     * @param requiredArgs List of names of required arguments
     */
    public UsageException(String exampleUsage, String... requiredArgs) {
        this.exampleUsage = exampleUsage;
        this.requiredArgs = requiredArgs;
    }

    @Override
    public String toString() {
        return String.format("usage: %s\n"
                + "error: the following arguments are required: %s",
                exampleUsage, String.join(", ", requiredArgs));
    }
}
