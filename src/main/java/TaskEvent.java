/**
 * Task that starts and ends at specific datetimes.
 */
public class TaskEvent extends Task {
    protected String from;
    protected String to;

    TaskEvent(String name, String from, String to) {
        super(name);
        this.from = from.trim();
        this.to = to.trim();
    }

    @Override
    public String toString() {
        String status = isDone ? "X" : " ";

        // format based on the toString output of `Task`
        return String.format("[D]%s (from: %s to: %s)", super.toString(), from, to);
    }
}
