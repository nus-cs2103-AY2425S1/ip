package murphy.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import murphy.MurphyException;

public class Deadline extends Task {
    protected LocalDate by;
    public Deadline(String description, String by) throws MurphyException {
        super(description);
        String byTrimmed = by.trim();
        if (byTrimmed.isEmpty()) {
            throw new MurphyException("Deadline by date cannot be empty!");
        }
        try {
            this.by = LocalDate.parse(byTrimmed);
        } catch (DateTimeParseException e) {
            throw new MurphyException("Date should be in the format yyyy-mm-dd.");
        }
    }

    public Deadline(String description, boolean isDone, String by) throws MurphyException {
        super(description, isDone);
        String byTrimmed = by.trim();
        if (byTrimmed.isEmpty()) {
            throw new MurphyException("Deadline by date cannot be empty!");
        }
        try {
            this.by = LocalDate.parse(byTrimmed);
        } catch (DateTimeParseException e) {
            throw new MurphyException("Date should be in the format yyyy-mm-dd.");
        }
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        return "[D]" + super.toString() + " (by: " + this.by.format(formatter) + ")";
    }

    @Override
    public String toSaveString() {
        return "D|" + super.toSaveString() + "|" + this.by;
    }
}
