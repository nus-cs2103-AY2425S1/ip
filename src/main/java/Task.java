public class Task {
    private final String command;
    protected final Boolean hasCompleted;

    Task(String command) {
        this.command = command;
        this.hasCompleted = false;
    }

    Task(String command, Boolean hasCompleted) {
        this.command = command;
        this.hasCompleted = hasCompleted;
    }

    Boolean getHasCompleted() {
        return hasCompleted;
    }

    @Override
    public String toString() {
        return command;
    }
}
