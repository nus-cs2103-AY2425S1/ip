package bob;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    private LocalDateTime by;
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");

    public Deadline(String description, String by, TaskType taskType) throws ChatBotException {
        super(description, taskType);
        try {
            this.by = LocalDateTime.parse(by, INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new ChatBotException("Invalid date format. Please use yyyy-MM-dd HHmm (e.g. 2024-08-28 1945).");
        }
    }

    public Deadline(String description, boolean isDone, String date, TaskType taskType) throws ChatBotException {
        super(description, isDone, taskType);
        try {
            this.by = LocalDateTime.parse(date, INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new ChatBotException("Invalid date format. Please use yyyy-MM-dd HHmm (e.g. 2024-08-28 1945).");
        }
    }

    public LocalDateTime getDeadline() {
        return this.by;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + this.by.format(OUTPUT_FORMAT) + ")";
    }

    @Override
    public String formatToSave() {
        return super.formatToSave() + " | " + this.by.format(INPUT_FORMAT);
    }
}