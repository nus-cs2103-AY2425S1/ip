public final class Query {
    private final String command;
    private final String details;
    public Query(String command, String details) {
        this.details = details;
        this.command = command;
    }

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
