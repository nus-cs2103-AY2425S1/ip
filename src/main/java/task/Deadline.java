package task;

import exception.DeadlineEmptyException;
import exception.TaskNameEmptyException;

public class Deadline extends Task {
    private final String deadline;

    public Deadline(String taskName, String deadline) throws TaskNameEmptyException, DeadlineEmptyException {
        super(taskName);
        if (deadline.isBlank()) {
            throw new DeadlineEmptyException();
        }
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }
}
