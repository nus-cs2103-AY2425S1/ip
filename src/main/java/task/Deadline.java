package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import exception.DukeException;

/**
 * This class represents a type of Task that has a deadline
 */
public class Deadline extends Task {

    private String deadline;
    private LocalDate formattedDeadline;

    /**
     * Constructor for task of type Deadline which is a task with a deadline and description
     * @param description The description of task
     * @param deadline Deadline at which task is to be completed
     * @throws DukeException An exception that happens due to invalid input
     */
    public Deadline(String description, String deadline) throws DukeException {
        super(description);
        if (description.isEmpty() || description.equals("deadline")) {
            throw new DukeException("deadline", "OOPS!!! The description of a deadline shouldn't be empty!\n");
        }
        this.deadline = deadline;
        try {
            formattedDeadline = LocalDate.parse(deadline);
        } catch (DateTimeParseException e) {
            formattedDeadline = null;
        }
    }

    @Override
    public String getString() {
        return "[D]" + super.getString() + " (by: " + ((formattedDeadline != null)
                ? formattedDeadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                    : this.deadline) + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Deadline) {
            Deadline other = (Deadline) obj;
            return this.description.equals(other.description) && this.deadline.equals(other.deadline);
        }
        return false;
    }

    @Override
    public String getStorageFormat() {
        String output = this.isDone ? "1" : "0";
        output += " deadline " + description + " /by " + deadline + "\n";
        return output;
    }

    public void setDeadline(String deadline) {
        String trimmedDeadline = deadline.trim();
        if (trimmedDeadline.isEmpty() || trimmedDeadline.equals("deadline")) {
            return;
        }
        this.deadline = trimmedDeadline;
        try {
            formattedDeadline = LocalDate.parse(deadline);
        } catch (DateTimeParseException e) {
            formattedDeadline = null;
        }
    }
}
