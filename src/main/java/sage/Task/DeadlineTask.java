package sage.Task;

import sage.SageException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DeadlineTask extends Task {

    protected LocalDateTime by;
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");

    /**
     * Constructs a DeadlineTask with a description and a deadline.
     *
     * @param description The description of the task.
     * @param by The deadline of the task in yyyy-MM-dd HHmm format.
     * @throws SageException If the deadline format is invalid.
     */
    public DeadlineTask(String description, String by) throws SageException {
        super(description);
        try {
            this.by = LocalDateTime.parse(by, INPUT_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new SageException("Please format your deadline date in yyyy-MM-dd HHmm");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(OUTPUT_FORMATTER) + ")";
    }

    @Override
    public String toSave() {
        return String.format("D | %s | %s", super.toSave(), by.format(INPUT_FORMATTER));
    }
}