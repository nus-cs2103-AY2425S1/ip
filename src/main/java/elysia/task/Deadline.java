package elysia.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Extends the Task class to include deadline-specific details.
 **/
public class Deadline extends Task {
    private final LocalDate deadline;

    /**
     * Constructs a Deadline task with the specified description and due date.
     *
     * @param description The description of the task.
     * @param deadline          The due date or time of the task.
     */
    public Deadline(String description, LocalDate deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Passes deadline to Comparator for sorting steps afterwards.
     *
     * @return
     */
    public LocalDate getDeadline() {
        return this.deadline;
    }

    /**
     * Returns a string representation of the Deadline task, including its description and due date.
     *
     * @return
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + deadline.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ")";
    }


    @Override
    public String saveToTxt() {
        int i = this.isDone ? 1 : 0;
        return "D | " + i + " | " + this.description + " | " + this.deadline;
    }
}
