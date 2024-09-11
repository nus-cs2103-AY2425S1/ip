package duck.exceptions;

public class UsageException extends Exception {
    private final String exampleUsage;
    private final String[] requiredArgs;

    public UsageException(String exampleUsage, String... requiredArgs) {
        this.exampleUsage = exampleUsage;
        this.requiredArgs = requiredArgs;
    }

    public String toString() {
        return String.format("usage: %s\n"
                + "error: the following arguments are required: %s",
                exampleUsage, String.join(", ", requiredArgs));
    }
}
