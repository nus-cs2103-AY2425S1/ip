package botimusprime.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime deadline;

    public Deadline(String description, boolean isDone, LocalDateTime deadline) {
        super(description, isDone);
        this.deadline = deadline;
    }

    @Override
    public boolean isEvent() {
        return false;
    }

    @Override
    public boolean isToDo() {
        return false;
    }

    @Override
    public boolean isDeadline() {
        return true;
    }

    public String saveString() {
        return String.format("D | %s | %s | %s", description, isDone,
                deadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                deadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
    }
}