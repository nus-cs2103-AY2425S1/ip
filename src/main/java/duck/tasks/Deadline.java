package duck.tasks;

import duck.exceptions.DeadlineUsageException;

public class Deadline extends Task {
    private final DateAndTime deadline;

    public Deadline(String description, DateAndTime deadline) throws DeadlineUsageException {
        super(description);

        if (description == null || description.equals("")
                || deadline == null) {
            throw new DeadlineUsageException();
        }

        this.deadline = deadline;
    }

    @Override
    public String getSaveString() {
        return String.format("D,%s,%s,%s", isDone, description, deadline.getOriginalString());
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + String.format(" (by: %s)", deadline.toString());
    }
}
