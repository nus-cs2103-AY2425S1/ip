package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import exception.DukeException;

/**
 * This class represents a type of Task that has a deadline
 */
public class Deadline extends Task{

    String deadline;
    LocalDate formattedDeadline;

    /**
     * Constructor for task of type Deadline which is a task with a deadline and description
     * @param description The description of task
     * @param deadline Deadline at which task is to be completed
     * @throws DukeException An exception that happens due to invalid input
     */
    public Deadline(String description, String deadline) throws DukeException{
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
        return "[D]" + super.getString() + " (by: " + ((formattedDeadline != null) ? formattedDeadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) : this.deadline) + ")";
    }

    @Override
    public String getStorageFormat() {
        String output = this.isDone ? "1" : "0";
        output += " deadline " + description + " /by " + deadline + "\n";
        return output;
    }
}
