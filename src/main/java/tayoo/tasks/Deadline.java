package tayoo.tasks;

import tayoo.Parser;
import tayoo.exception.TayooException;

import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Objects;

public class Deadline extends Task {
    private final TemporalAccessor deadline;
    private String deadlineStr;


    /**
     * Constructs a new 'Deadline' task that can be added to Tayoo's list. A Deadline is defined as a task
     * that has a deadline that it needs to be completed before
     *
     * @param title title of the Deadline task
     * @param deadline Deadline by which the task should be completed
     */
    public Deadline(String title, String deadline, boolean completed) {
        super(title, completed);
        this.deadlineStr = deadline;
        this.deadline = Parser.parseDateTime(deadline);
    }

    /**
     * Constructs a new 'Deadline' task that can be added to Tayoo's list. A Deadline is defined as a task
     * that has a deadline that it needs to be completed before. Assumes new Deadline is not completed.
     *
     * @param title title of the Deadline task
     * @param deadline Deadline by which the task should be completed
     */
    public Deadline(String title, String deadline) {
        super(title);
        this.deadlineStr = deadline;
        this.deadline = Parser.parseDateTime(deadline);
    }

    @Override
    public String toString() {

        String deadlineReturnString;

        if (this.deadline != null) {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");
            deadlineReturnString = dateFormatter.format(this.deadline);
        } else {
            deadlineReturnString = Objects.requireNonNullElse(deadlineStr, "");
        }

        return "[D]" + super.toString() + " (by: " + deadlineReturnString + ")";
    }

    /**
     * Returns the Deadline in a form that can be stored as data in a .txt file
     * Tasks will be stored in the format "Deadline" | [true OR false] | [Deadline title] | [deadline]. The value true
     * will be stored if the Deadline is completed, and false otherwise.
     *
     * @return string representation of Deadline in command form
     */
    public String toTxt() throws TayooException {

        String deadlineTxtStr;

        if (deadline != null) {
            deadlineTxtStr = deadline.toString();
        } else if (deadlineStr != null){
            deadlineTxtStr = deadlineStr;
        } else {
            throw new TayooException("No deadline string");
        }

        return "Deadline | " + isCompleted() + " | " + this.getTitle() + " | " + deadlineTxtStr;
    }
}
