package models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    public static final char TASK_TYPE = 'D';
    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final LocalDate by;
    public Deadline(String name, LocalDate by) {
        super(name);
        this.by = by;
    }

    @Override
    public char getTaskType() {
        return TASK_TYPE;
    }

    public static Deadline deserialize(String line) {
        String[] values = line.split("\\|");
        Deadline deadline = new Deadline(values[2], LocalDate.parse(values[3]));
        if (values[1].equals("X")) {
            deadline.markDone();
        }
        return deadline;
    }

    @Override
    public String serialize() {
        return String.format(
            "%s|%c|%s|%s",
            this.getTaskType(),
            this.isDone ? 'X' : 'O',
            this.name,
            this.by
        );
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" (by: %s)", this.by.format(dateFormat));
    }
}
