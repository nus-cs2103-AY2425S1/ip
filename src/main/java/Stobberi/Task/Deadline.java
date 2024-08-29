package Stobberi.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime deadlineOfTask;

    public Deadline(String description, String deadlineOfTask) {
        super(description);
        this.deadlineOfTask = LocalDateTime.parse(deadlineOfTask, DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm'hrs'"));
    }

    public Deadline(String description, String deadlineOfTask, String done) {
        super(description);
        this.deadlineOfTask = LocalDateTime.parse(deadlineOfTask, DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm'hrs'"));
        if (done.equals("1")) {super.setDone();}
    }

    public boolean isDuring(String date) {
        LocalDateTime start = LocalDateTime.parse(date + " 0000hrs", DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm'hrs'"));
        LocalDateTime end = LocalDateTime.parse(date + " 2359hrs", DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm'hrs'"));

        return (deadlineOfTask.isAfter(start) || deadlineOfTask.isEqual(start)) &&
                (deadlineOfTask.isBefore(end) || deadlineOfTask.isEqual(end));
    }

    public String getDeadlineOfTask() {
        return deadlineOfTask.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm'hrs'"));
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + deadlineOfTask.format(DateTimeFormatter.ofPattern("d MMMM yyyy ha")) + ")";
    }
}