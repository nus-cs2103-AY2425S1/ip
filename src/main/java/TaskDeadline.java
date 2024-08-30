/**
 * Task with a deadline.
 */
public class TaskDeadline extends Task {
    protected String by;

    TaskDeadline(String name, String by) throws TorneInvalidCommandException {
        super(name);

        // check if by is empty
        if (by == null || by.isBlank()) {
            throw new TorneInvalidCommandException("Option /by cannot be empty");
        }

        this.by = by.trim();
    }

    @Override
    public String toString() {
        String status = isDone ? "X" : " ";

        // format based on the toString output of `Task`
        return String.format("[D]%s (by: %s)", super.toString(), by);
    }
}
