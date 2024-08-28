package main.java;

import java.time.LocalDate;

public class Deadline extends Task {
    private LocalDate deadline;
    public Deadline(String description, boolean isDone, LocalDate deadline) {
        super(description, "D", isDone);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        String superString = super.toString();
        String dueDate = String.format(" (by: %s)", this.formatDate(this.deadline));
        return superString + dueDate;
    }

    public String getDeadline() {
        return this.deadline.toString();
    }
}
