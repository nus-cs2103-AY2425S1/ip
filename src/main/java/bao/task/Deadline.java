package bao.task;

import bao.main.Bao;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Deadline extends Task {
    private LocalDateTime deadline;
    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    public LocalDateTime getDate() {
        return deadline;
    }

    @Override
    public String toString() {
        return "D | " + super.toString() + " | " + deadline.format(Bao.outputDateFormat);
    }

    @Override
    public String toFileString() {
        return "D | " + (isDone ? "1" : "0") + " | " + description.trim() + " | " + deadline.format(Bao.fileDateFormat);

    }
}
