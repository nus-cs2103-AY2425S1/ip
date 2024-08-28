package main.java.chatbot.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task {
    private LocalDate by;

    public DeadlineTask(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    public DeadlineTask(String description, LocalDate by, boolean isDone) {
        super(description);
        this.by = by;
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[D]" + getStatusIcon() + description + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String toSaveFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by.format(formatter);
    }

    public LocalDate getBy() {
        return by;
    }
}
