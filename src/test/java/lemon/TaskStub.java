package lemon;

import lemon.task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TaskStub extends Task {
    private LocalDate date;

    public TaskStub() {
        super(" Test ", "Stub", false);

        LocalDate date = LocalDate.now();
        date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        this.date = date;
    }
    @Override
    public String toFileString() {
        return "S|" + isDone + "|" + description + "|" + date.toString() + "\n";
    }

    @Override
    public String toString() {
        return "[S]" + super.toString() + "(date: " +
                date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
