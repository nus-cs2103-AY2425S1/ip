package conversage.task;

import conversage.exception.ConverSageException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an event task with a start and end time.
 */
public class Event extends Task {
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy, h:mma");

    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs an Event task with the specified description, start time, and end time.
     *
     * @param taskDesc the description of the task.
     * @param from     the start time of the event.
     * @param to       the end time of the event.
     * @throws ConverSageException if the date/time format is invalid.
     */
    public Event(String taskDesc, String from, String to) throws ConverSageException {
        super(taskDesc);

        try {
            this.from = LocalDateTime.parse(from, INPUT_FORMAT);
            this.to = LocalDateTime.parse(to, INPUT_FORMAT);

        } catch (DateTimeParseException e) {
            System.out.println("The provided datE/time format is invalid! "
                    + "Please use the following format: yyyy-MM-dd HH:mm.");
        }
    }

    @Override
    public String toFileFormat() {
        return "conversage.task.Event | " + (isDone ? "Done" : "Not Done") + " | "
                + taskDesc + " | " + from.format(INPUT_FORMAT) + " | " + to.format(INPUT_FORMAT);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(OUTPUT_FORMAT)
                + " to: " + to.format(OUTPUT_FORMAT) + ")";
    }
}
