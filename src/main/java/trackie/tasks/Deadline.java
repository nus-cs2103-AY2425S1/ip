package trackie.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Represents a class of the type "Deadline".
 */
public class Deadline extends Task {
    private String type = "D";
    private String dateTimePattern = "yyyyMMdd HHmm";

    private LocalDateTime deadline;

    private DateTimeFormatter parsingFormatter = DateTimeFormatter.ofPattern(dateTimePattern);
    private DateTimeFormatter readingFormatter = DateTimeFormatter.ofLocalizedDateTime(
            FormatStyle.MEDIUM,
            FormatStyle.MEDIUM
    );




    /**
     * Creates a deadline task.
     *
     * @param description the description of the deadline task.
     * @param deadline the deadline of the task, given in yyyy-mm-dd format.
     */
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = LocalDateTime.parse(deadline, parsingFormatter);
    }

    /**
     * Creates a deadline task with a custom completion status.
     *
     * @param description the description of the deadline task.
     * @param deadline the deadline of the task, given in yyyy-mm-dd.
     * @param status the completion of the status of the task.
     */
    public Deadline(String description, String deadline, int status) {
        super(description, status);
        this.deadline = LocalDateTime.parse(deadline, parsingFormatter);
    }

    /**
     * Retrieves relevant information about the task.
     *
     * @return A String containing the description and the deadline of the task.
     */
    @Override
    public String toString() {
        return(String.format("%s (by: %s)", super.description, this.deadline.format(readingFormatter)));
    }

    /**
     * Retrieves the type of the task.
     *
     * @return A String denoting the type of the task. In this case, the type is "D".
     */
    public String getTaskType() {
        return(this.type);
    }

    /**
     * Retrieves the deadline of the task.
     *
     * @return A String representation of the deadline of the task, in yyyy-mm-dd.
     */
    public String getDeadline() {
        return this.deadline.format(parsingFormatter);
    }
}
