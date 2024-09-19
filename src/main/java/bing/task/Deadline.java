package bing.task;

import bing.task.Task;
import bing.task.TaskStatus;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime deadline;

    public Deadline(String info, LocalDateTime deadline) {
        super(info);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy HHmm");
        return "[D]" + "[" + getStatus().getStatusSymbol() + "]" + " " + getInfo() + " (by: " + deadline.format(formatter) + ")";
    }

    @Override
    public String toFileFormat(DateTimeFormatter formatter) {
        return "D | " + (getStatus() == TaskStatus.DONE ? "1" : "0") + " | " + getInfo() + " | " + deadline.format(formatter);
    }
}
