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
        assert !by.isEmpty();
        if (by.contains("-")) {
            String[] parseDT = by.split("-");
            this.deadline = LocalDateTime.of(Integer.parseInt(parseDT[0]),
                    Integer.parseInt(parseDT[1]), Integer.parseInt(parseDT[2]), 23, 59);
        } else {
            this.deadline = LocalDateTime.parse(by, FORMAT);
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
