package maga.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Task {
    protected String description;
    protected boolean isDone;
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] "); // mark done maga.task with X
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsUndone() {
        isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public abstract String getTaskType();

    public abstract String printTask();

    public static Task fromString(String taskString) {
        String[] parts = taskString.split(" \\| ");
        if (parts.length == 3) {
            String type = parts[0];
            boolean isDone = parts[1].equals("1");
            String description = parts[2];
            return new TodoTask(isDone, description);
        } else if (parts.length == 4) {
            String type = parts[0];
            boolean isDone = parts[1].equals("1");
            String description = parts[2];
            LocalDate dateTime = LocalDate.parse(parts[3], formatter); // need handle error
            return new EventTask(isDone, description, dateTime);
        } else if (parts.length == 5) {
            String type = parts[0];
            boolean isDone = parts[1].equals("1");
            String description = parts[2];
            LocalDate startDate = LocalDate.parse(parts[3], formatter); // need handle error
            LocalDate endDate = LocalDate.parse(parts[4], formatter);
            return new DeadlineTask(isDone, description, startDate, endDate);
        }

        return new TodoTask(false, "");
    }

    @Override
    public abstract String toString();
}
