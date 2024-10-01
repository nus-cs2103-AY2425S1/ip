package flychat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;

import flychat.tasks.Task;

/**
 * Represents the deadline task type.
 */
public class Deadline extends Task {

    private LocalDate deadline;

    private Deadline(String description, LocalDate deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Creates a deadline task.
     *
     * @param description String description of the deadline task.
     * @param deadlineText String form of the deadline end date.
     * @param isMarked Boolean indicating if the task is marked.
     * @return New deadline object.
     * @throws InputMismatchException If the input does not contain a description or has the end date formatted wrongly.
     */
    public static Deadline createNewDeadline(String description, String deadlineText, boolean isMarked) throws
            InputMismatchException {
        if (description.equals("")) {
            throw new InputMismatchException("Please ensure that input contains a description TT");
        }

        try {
            Deadline newDeadline = new Deadline(description, LocalDate.parse(deadlineText));
            if (isMarked) {
                newDeadline.completeTask();
            }
            return newDeadline;
        } catch (DateTimeParseException e) {
            throw new InputMismatchException(
                    "Please ensure that input contains end date formatted in the yyyy-mm-dd format");
        }
    }

    @Override
    public String formatStringForSaving() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline.format(
                DateTimeFormatter.ofPattern("MM dd yyyy")) + ")";
    }
}
