package noosy.task;

import noosy.task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private LocalDate due;
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy");

    public Deadline(String name, LocalDate due) {
        super(name);
        this.due = due;
    }

    public Deadline(String name, boolean status, LocalDate due) {
        super(name, status);
        this.due = due;
    }

    public LocalDate getDate() {
        return this.due;
    }

    @Override
    public String storeInFile() {
        String formattedDate = (due != null) ? due.format(OUTPUT_FORMATTER) : "Date can't be processed by noosy.Noosy";
        return String.format("T | %s | %s | %s", super.storeInFile(), this.name, this.due);
    }

    @Override
    public String toString() {
        String formattedDate = (due != null) ? due.format(OUTPUT_FORMATTER) : "Date can't be processed by noosy.Noosy";
        String desc = String.format("[D]%s (by: %s)", super.toString(), formattedDate);
        return desc;
    }
}
