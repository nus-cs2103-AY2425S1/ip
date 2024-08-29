package task;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private LocalDateTime timeDeadline;
    private LocalDate dateDeadline;

    public Deadline(String name, LocalDateTime deadline) {
        super(name);
        this.timeDeadline = deadline;
    }

    public Deadline(String name, LocalDate deadline) {
        super(name);
        this.dateDeadline = deadline;
    }

    private String getDisplayStringDeadline() {
        if (this.timeDeadline != null) {
            return this.timeDeadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
        } else {
            return this.dateDeadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        }
    }

    private String getStringDeadline() {
        if (this.timeDeadline != null) {
            return this.timeDeadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        } else {
            return this.dateDeadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
    }

    public Deadline(String name, LocalDateTime deadline, boolean isDone) {
        super(name, isDone);
        this.timeDeadline = deadline;
    }

    public Deadline(String name, LocalDate deadline, boolean isDone) {
        super(name, isDone);
        this.dateDeadline = deadline;
    }

    @Override
    public String toFileString() {
        String done = this.isDone() ? "1" : "0";
        String stringDeadline = this.getStringDeadline();
        return "D," + done + "," + this.getName() + "," + stringDeadline;
    }

    @Override
    public String toString() {
        String stringDeadline = this.getDisplayStringDeadline();
        return "[D] " + super.toString() + " (by: " + stringDeadline + ")";
    }
}
