package tasks;

import exceptions.DeadlineUsageException;

public class Deadline extends Task {
    private final String deadline;

    public Deadline(String description, String deadline) throws DeadlineUsageException {
        super(description);

        if (description == null || description.equals("")
                || deadline == null || deadline.equals("")) {
            throw new DeadlineUsageException();
        }

        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + String.format(" (by: %s)", deadline.toString());
    }
}
