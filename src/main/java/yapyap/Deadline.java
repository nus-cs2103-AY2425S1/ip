package yapyap;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    private LocalDateTime byDateTime;
    private LocalDate byDate;
    private boolean isDateTimeFormat;

    public Deadline (String description, String by) throws YapperBotException {
        super(description);
        this.byDate = null;
        this.byDateTime = null;
        this.isDateTimeFormat = false;

        try {
            this.byDateTime = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
            this.isDateTimeFormat = true;
        } catch (DateTimeParseException e1) {
            try {
                this.byDate = LocalDate.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                this.isDateTimeFormat = false;
            } catch (DateTimeParseException e2) {
                throw new YapperBotException("Invalid date format! Please use either 'd/M/yyyy HHmm' "
                        + "or 'yyyy-MM-dd'.");
            }
        }
    }

    public Deadline (String description, String by, boolean isDone) throws YapperBotException {
        super(description);
        this.byDate = null;
        this.byDateTime = null;
        this.isDateTimeFormat = false;
        this.isDone = isDone;

        try {
            this.byDateTime = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
            this.isDateTimeFormat = true;
        } catch (DateTimeParseException e1) {
            try {
                this.byDate = LocalDate.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                this.isDateTimeFormat = false;
            } catch (DateTimeParseException e2) {
                throw new YapperBotException("Invalid date format! Please use either 'd/M/yyyy HHmm' "
                        + "or 'yyyy-MM-dd'.");
            }
        }
    }

    @Override
    public String toString() {
        if (isDateTimeFormat) {
            return "[D]" + super.toString() + " (by: "
                    + this.byDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy h:mm a")) + ")";
        } else {
            return "[D]" + super.toString() + " (by: "
                    + this.byDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
        }
    }

    @Override
    public String toSaveFormat() {
        if (this.isDateTimeFormat) {
            return "D | " + (this.isDone ? 1 : 0) + " | " + this.description
                    + " | " + this.byDateTime.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        } else {
            return "D | " + (this.isDone ? 1 : 0) + " | " + this.description
                    + " | " + this.byDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
    }
}
