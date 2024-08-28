package bob;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;

    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");

    public Event(String description, String from, String to, TaskType type) throws ChatBotException {
        super(description, type);
        try {
            this.from = LocalDateTime.parse(from, INPUT_FORMAT);
            this.to = LocalDateTime.parse(to, INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new ChatBotException("Invalid date format. Please use yyyy-MM-dd HHmm (e.g. 2024-08-28 1945).");
        }
    }

    public Event(String description, boolean isDone, String from, String to, TaskType type) throws ChatBotException {
        super(description, isDone, type);
        try {
            this.from = LocalDateTime.parse(from, INPUT_FORMAT);
            this.to = LocalDateTime.parse(to, INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new ChatBotException("Invalid date format. Please use yyyy-MM-dd HHmm (e.g. 2024-08-28 1945).");
        }
    }

    public LocalDateTime getFrom() {
        return this.from;
    }

    public LocalDateTime getTo() {
        return this.to;
    }

    @Override
    public String toString() {
        return super.toString() + " (from: " + this.from.format(OUTPUT_FORMAT) +
                " to: " + this.to.format(OUTPUT_FORMAT) + ")";
    }

    @Override
    public String formatToSave() {
        return super.formatToSave() + " | " + this.from.format(INPUT_FORMAT) + " | " + this.to.format(INPUT_FORMAT);
    }
}