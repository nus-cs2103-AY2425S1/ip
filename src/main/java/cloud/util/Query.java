package cloud.util;

public final class Query {
    private final String command;
    private final String details;

    /**
     * Constructs a Query object
     *
     * @param command string representation of the command
     * @param details additional details of the query
     */
    public Query(String command, String details) {
        this.details = details;
        this.command = command;
    }

    /**
     * Constructs a Query object
     *
     * @param command string representation of the command
     */
    public Query(String command) {
        this.command = command;
        this.details = null;
    }

    public String getCommand() {
        return this.command;
    }

    public String getDetails() {
        return this.details;
    }
}
