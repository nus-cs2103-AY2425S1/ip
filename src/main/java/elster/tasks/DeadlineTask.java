package elster.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import elster.Elseption;


/**
 * Deadline tasks are tasks that have a deadline on top of the base task functionalities
 */
public class DeadlineTask extends Task {
    private final LocalDateTime deadline;
    private DeadlineTask(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Factory method for a deadline task.
     * Parses input string to create a deadline task with a description and a deadline.
     *
     * @param input Input from terminal to be parsed.
     * @return Created deadline task.
     */
    public static DeadlineTask of(String input) throws Elseption {
        LocalDateTime deadline;

        if (input.strip().equals("deadline")) {
            throw new Elseption("Elster begs of you to have details for your task");

        } else if (!input.contains("/by")) {
            throw new Elseption("Elster begs of you to have a yknow, deadline with /by");

        }

        try {
            String deadlineStr = input.substring(input.indexOf("/by") + 4).strip();

            if (deadlineStr.length() == 10) {
                deadline = LocalDate.parse(deadlineStr).atTime(23, 59);

            } else {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                deadline = LocalDateTime.parse(deadlineStr, formatter);
            }

        } catch (Exception e) {
            throw new Elseption("for /by, Elster requires a yyyy-mm-dd or yyyy-mm-dd HH:mm "
                    + "format please and thanks");
        }

        return new DeadlineTask(
                input.substring(8, input.indexOf("/by")).strip(),
                deadline
        );
    }

    @Override
    public String toString() {
        StringBuilder resultStr = new StringBuilder();
        if (this.status) {
            resultStr
                    .append("[D][X] ")
                    .append(this.description)
                    .append(" (by: ")
                    .append(deadline)
                    .append(")");
        } else {
            resultStr
                    .append("[D][ ] ")
                    .append(this.description)
                    .append(" (by: ")
                    .append(deadline)
                    .append(")");
        }
        for (String tag : tags) {
            resultStr.append(" #").append(tag);
        }
        return resultStr.toString();
    }

    /**
     * Returns a string representation of the deadline task suitable for writing to the save file.
     *
     * @return String representation of deadline formatted for file writing.
     */
    @Override
    public String toFileString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        if (this.status) {
            return "D | 1 | " + this.description + " | " + this.deadline.format(formatter);
        } else {
            return "D | 0 | " + this.description + " | " + this.deadline.format(formatter);
        }
    }
}
