package bobby;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import bobby.exception.EmptyDescriptionException;

/**
 * Deadline is a type of task.
 * It represents a task with a description and a due date.
 */
public class Deadline extends Task {
    LocalDate deadline;

    /**
     * Constructs a deadline task with a description and due date.
     * @param s Description of the task.
     * @param deadline Due date of the task.
     */
    public Deadline(String s, String deadline) {
        super(s);
        this.deadline = LocalDate.parse(deadline);
    }

    /**
     * Creates a deadline task from a string input given by the user.
     * @param input Command given by the user.
     * @return The Deadline task created from the input.
     * @throws EmptyDescriptionException If the user did not provide any description.
     */
    public static Deadline createDeadline(String input) throws EmptyDescriptionException {
        String deadlineDescription = input.substring(8).trim();
        if (deadlineDescription.isEmpty()) {
            throw new EmptyDescriptionException("deadline");
        }
        String deadlineName = deadlineDescription.split(" /by ")[0];
        String deadlineDay = deadlineDescription.split(" /by ")[1];
        return new Deadline(deadlineName, deadlineDay);
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getTaskType() {
        return "D";
    }

    /**
     * @inheritDoc
     */
    @Override
    public String taskInFile() {
        return String.format("D | %s | %s | /by %s", this.getStatusIcon(), this.getDescription(),
                this.deadline);
    }
}
