public class Task {
    protected final String command;

    Task(String command) {
        this.command = command;
    }

    @Override
    public String toString() {
        return command;
    }
}
