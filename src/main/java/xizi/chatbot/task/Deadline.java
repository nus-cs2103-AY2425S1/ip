package xizi.chatbot.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline. This class extends the {@link Task} class
 * and includes additional information about the deadline.
 */
public class Deadline extends Task {
    protected LocalDateTime ddl;
    private static final DateTimeFormatter OUTPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy, h:mma");
    private static final DateTimeFormatter INPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    /**
     * Constructs a {@code Deadline} task with the specified description and deadline.
     *
     * @param description The description of the task.
     * @param deadline    The deadline of the task.
     */
    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.ddl = deadline;
    }

    public LocalDateTime getDdl(){
        return this.ddl;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + ddl.format(OUTPUT_DATE_FORMAT) + ")";
    }

    /**
     * Returns the string representation of the {@code Deadline} task in the format stored in data file.
     * The format includes task type, status, description, and deadline.
     *
     * @return A string in the file format to save the task.
     */
    @Override
    public String toFileFormat(){
        return String.format("D | %d | %s | %s", this.isDone ? 1 : 0, this.name, this.ddl.format(OUTPUT_DATE_FORMAT));
    }

}
