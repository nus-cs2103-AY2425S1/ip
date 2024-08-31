package tars.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    LocalDate deadlineDate;

    public Deadline(String name, LocalDate deadlineDate) {

        super(name);
        this.deadlineDate = deadlineDate;
    }


    @Override
    public String saveTask() {

        return String.format("D|%s|%s|%s", super.getStatus(), super.getTask(),
                deadlineDate.format(DateTimeFormatter.ofPattern("dd-MM-yy")));
    }

    @Override
    public String toString() {
        return String.format("[D] %s (by: %s)", super.toString(),
                deadlineDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
