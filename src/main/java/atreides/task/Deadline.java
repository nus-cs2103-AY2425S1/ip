package atreides.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
    protected String by;
    protected LocalDateTime deadline;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        if (by.contains("-")) {
            String[] parseDT = by.split("-");
            this.deadline = LocalDateTime.of(Integer.parseInt(parseDT[0]),
                    Integer.parseInt(parseDT[1]), Integer.parseInt(parseDT[2]), 23, 59);
        } else if (validateDate(by)) {
            this.deadline = LocalDateTime.parse(by, FORMAT);
        }
    }

    public boolean validateDate(String date) {
        try {
            LocalDateTime.parse(date, FORMAT);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String write() {
        return "D" + super.write() + " | " + deadline.format(FORMAT);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline.format(FORMAT) + ")";
    }
}
