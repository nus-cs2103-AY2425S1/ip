package bob;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a deadline task in the Bob chatbot.
 * The deadline includes a description, a completion status, a deadline date/time, and a task type.
 */
public class Deadline extends Task {

    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
    private LocalDateTime by;

    /**
     * Constructs a Deadline task with a description and deadline date/time.
     *
     * @param description The description of the deadline task.
     * @param by The deadline date/time in the format "yyyy-MM-dd HHmm".
     * @param taskType The type of the task (e.g., DEADLINE).
     * @throws ChatBotException if the date/time format is invalid.
     */
    public Deadline(String description, String by, TaskType taskType) throws ChatBotException {
        super(description, taskType);
        try {
            this.by = LocalDateTime.parse(by, INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new ChatBotException("Invalid date format. Please use yyyy-MM-dd HHmm (e.g. 2024-08-28 1945).");
        }
    }

    /**
     * Constructs a Deadline task with a description, completion status, and deadline date/time.
     *
     * @param description The description of the deadline task.
     * @param isDone The completion status of the task.
     * @param date The deadline date/time in the format "yyyy-MM-dd HHmm".
     * @param taskType The type of the task (e.g., DEADLINE).
     * @throws ChatBotException if the date/time format is invalid.
     */
    public Deadline(String description, boolean isDone, String date, TaskType taskType) throws ChatBotException {
        super(description, isDone, taskType);
        try {
            this.by = LocalDateTime.parse(date, INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new ChatBotException("Invalid date format. Please use yyyy-MM-dd HHmm (e.g. 2024-08-28 1945).");
        }
    }

    /**
     * Returns the deadline date/time of the task.
     *
     * @return The deadline as a LocalDateTime object.
     */
    public LocalDateTime getDeadline() {
        return this.by;
    }

    /**
     * Returns a string representation of the deadline task, including its description and formatted deadline date/time.
     * This method overrides the {@link Task#toString()} method to provide a specific format for Deadline tasks.
     *
     * @return The string representation of the deadline task.
     * @inheritDoc
     */
    @Override
    public String toString() {
        return super.toString() + " (by: " + this.by.format(OUTPUT_FORMAT) + ")";
    }

    /**
     * Formats the deadline task for saving to a file.
     * The format includes the task's description, status, and deadline in the input format.
     * This method overrides the {@link Task#formatToSave()} method to provide a specific format for Deadline tasks.
     *
     * @return The formatted string for saving the task.
     * @inheritDoc
     */
    @Override
    public String formatToSave() {
        return super.formatToSave() + " | " + this.by.format(INPUT_FORMAT);
    }
}
