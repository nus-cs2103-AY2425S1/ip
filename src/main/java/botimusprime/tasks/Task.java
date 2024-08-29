package botimusprime.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public abstract boolean isEvent();

    public abstract boolean isToDo();

    public abstract boolean isDeadline();

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getDescription() {
        return description;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsUndone() {
        isDone = false;
    }

    public boolean isDone() {
        return isDone;
    }

    public String saveString() {
        return "placeholder";
    }

    public static Task fromString(String taskString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String[] parser = taskString.split(" \\| ");
        String type = parser[0];
        String description = parser[1];
        boolean isDone = Boolean.parseBoolean(parser[2]);

        if (Objects.equals(type, "T")) {
            return new ToDo(description, isDone);
        } else if (Objects.equals(type, "D")) {
            String deadline = parser[3];
            return new Deadline(description, isDone, LocalDateTime.parse(deadline, formatter));
        } else {
            String from = parser[3];
            String to = parser[4];
            return new Event(description, isDone, LocalDateTime.parse(from, formatter), LocalDateTime.parse(to, formatter));
        }

    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }


}