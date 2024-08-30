/**
 * Task that starts and ends at specific datetimes.
 */
public class TaskEvent extends Task {
    protected String from;
    protected String to;

    TaskEvent(String name, String from, String to) throws TorneInvalidCommandException {
        super(name);

        // now check from and to
        if (from == null || to == null || from.isBlank() || to.isBlank()) {
            throw new TorneInvalidCommandException("Options /from and /to cannot be empty");
        }

        this.from = from.trim();
        this.to = to.trim();
    }

    @Override
    public String toString() {
        String status = isDone ? "X" : " ";

        // format based on the toString output of `Task`
        return String.format("[E]%s (from: %s to: %s)", super.toString(), from, to);
    }
}
