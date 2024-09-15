package bob;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an event task in the Bob chatbot.
 * The event includes a description, a completion status, a start and end time, and a task type.
 */
public class Event extends Task {

    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Constructs an Event with the specified description, start and end time, and task type.
     *
     * @param description The description of the event.
     * @param from The start time of the event, in the format yyyy-MM-dd HHmm.
     * @param to The end time of the event, in the format yyyy-MM-dd HHmm.
     * @param type The task type (e.g., EVENT).
     * @throws ChatBotException if the date format is invalid.
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
     * Constructs an Event with the specified description, completion status, start and end time, and task type.
     *
     * @param description The description of the event.
     * @param isDone The completion status of the event.
     * @param from The start time of the event, in the format yyyy-MM-dd HHmm.
     * @param to The end time of the event, in the format yyyy-MM-dd HHmm.
     * @param type The task type (e.g., EVENT).
     * @throws ChatBotException if the date format is invalid.
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
     * Gets the start time of the event.
     *
     * @return The start time of the event.
     */
    public LocalDateTime getFrom() {
        return this.from;
    }

    /**
     * Gets the end time of the event.
     *
     * @return The end time of the event.
     */
    public LocalDateTime getTo() {
        return this.to;
    }

    /**
     * Returns a string representation of the event task, including its description and formatted start/end time.
     * This method overrides the {@link Task#toString()} method to provide a specific format for Event tasks.
     *
     * @return The string representation of the event task.
     * @inheritDoc
     */
    @Override
    public String toString() {
        return super.toString() + " (from: " + this.from.format(OUTPUT_FORMAT)
                + " to: " + this.to.format(OUTPUT_FORMAT) + ")";
    }

    /**
     * Formats the event task for saving to a file.
     * The format includes the task's description, status, and start/end times in the input format.
     * This method overrides the {@link Task#formatToSave()} method to provide a specific format for Event tasks.
     *
     * @return The formatted string for saving the task.
     * @inheritDoc
     */
    @Override
    public String formatToSave() {
        return super.formatToSave() + " | " + this.from.format(INPUT_FORMAT) + " | " + this.to.format(INPUT_FORMAT);
    }
}
