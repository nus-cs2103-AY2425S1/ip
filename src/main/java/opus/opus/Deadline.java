package opus;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected LocalDate byDateTime;
    protected String byString;

    public Deadline(String description, String by) {
        super(description);
        try {
            DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            this.byDateTime = LocalDate.parse(by, inputFormat);
        } catch (DateTimeParseException e) {
            this.byString = by;
        }
    }

    private String getBy() {
        if (byDateTime != null) {
            DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");
            return byDateTime.format(outputFormat);
        } else {
            return byString;
        }
    }

    @Override
    public String toString() {
        return "[D]" + (isDone ? "[X] " : "[ ] ") + description + " (by: " + getBy() + ")";
    }

    @Override
    public String toSaveFormat() {
        return "D|" + (isDone ? "1" : "0") + "|" + description + "|" + getBy();
    }

    public static Task fromFileFormat(String fullLine) {
        String[] parts = fullLine.split("\\|");
        Deadline deadline = new Deadline(parts[2], parts[3]);
        if (parts[1].equals("1")) {
            deadline.markAsDone();
        }
        return deadline;
    }
}
