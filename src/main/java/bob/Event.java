package bob;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an event task in the Bob chatbot.
 * The event includes a description, completion status, start time, end time, and task type.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");

    /**
     * Constructs an Event task with a description, start time, end time, and task type.
     *
     * @param description The description of the event task.
     * @param from The start date/time of the event in the format "yyyy-MM-dd HHmm".
     * @param to The end date/time of the event in the format "yyyy-MM-dd HHmm".
     * @param type The type of the task (e.g., EVENT).
     * @throws ChatBotException if the date/time format is invalid.
     */
    public Event(String description, String from, String to, TaskType type) throws ChatBotException {
        super(description, type);
        try {
            this.from = LocalDateTime.parse(from, INPUT_FORMAT);
            this.to = LocalDateTime.parse(to, INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new ChatBotException("Invalid date format. Please use yyyy-MM-dd HHmm (e.g. 2024-08-28 1945).");
        }
    }

    /**
     * Constructs an Event task with a description, completion status, start time, end time, and task type.
     *
     * @param description The description of the event task.
     * @param isDone The completion status of the task.
     * @param from The start date/time of the event in the format "yyyy-MM-dd HHmm".
     * @param to The end date/time of the event in the format "yyyy-MM-dd HHmm".
     * @param type The type of the task (e.g., EVENT).
     * @throws ChatBotException if the date/time format is invalid.
     */
    public Event(String description, boolean isDone, String from, String to, TaskType type) throws ChatBotException {
        super(description, isDone, type);
        try {
            this.from = LocalDateTime.parse(from, INPUT_FORMAT);
            this.to = LocalDateTime.parse(to, INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new ChatBotException("Invalid date format. Please use yyyy-MM-dd HHmm (e.g. 2024-08-28 1945).");
        }
    }

    /**
     * Returns the start date/time of the event.
     *
     * @return The start date/time as a LocalDateTime object.
     */
    public LocalDateTime getFrom() {
        return this.from;
    }

    /**
     * Returns the end date/time of the event.
     *
     * @return The end date/time as a LocalDateTime object.
     */
    public LocalDateTime getTo() {
        return this.to;
    }

    /**
     * Returns a string representation of the event task, including its description, start time, and end time.
     *
     * @return The string representation of the event task.
     * @inheritDoc
     */
    @Override
    public String toString() {
        return super.toString() + " (from: " + this.from.format(OUTPUT_FORMAT) +
                " to: " + this.to.format(OUTPUT_FORMAT) + ")";
    }

    /**
     * Formats the event task for saving to a file.
     * The format includes the task's description, status, start time, and end time in the input format.
     *
     * @return The formatted string for saving the task.
     * @inheritDoc
     */
    @Override
    public String formatToSave() {
        return super.formatToSave() + " | " + this.from.format(INPUT_FORMAT) + " | " + this.to.format(INPUT_FORMAT);
    }
}
