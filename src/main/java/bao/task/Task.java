package bao.task;

import bao.main.Bao;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public static Task fromString(String string) {
        String[] parts = string.split(" \\| ");
        if (parts.length < 3) {
            throw new IllegalArgumentException("Invalid task format: " + string);
        }
        String type = parts[0].trim();
        boolean isDone = parts[1].trim().equals("1");
        String description = parts[2].trim();

        switch (type) {
            case "T" -> {
                ToDo todo = new ToDo (description);
                if (isDone) {
                    todo.mark();
                }
                return todo;
            }
            case "D" -> {
                if (parts.length < 4) {
                    throw new IllegalArgumentException("Invalid deadline task format: " + string);
                }
                LocalDateTime dateAndTime = LocalDateTime.parse(parts[3].trim(), Bao.fileDateFormat);
                Deadline deadline = new Deadline(description, dateAndTime);
                if (isDone) {
                    deadline.mark();
                }
                return deadline;
            }
            case "E" -> {
                if (parts.length < 4) {
                    throw new IllegalArgumentException("Invalid deadline task format: " + string);
                }
                String[] duration = parts[3].split(" - ");
                if (duration.length < 2) {
                    throw new IllegalArgumentException("Invalid event duration format: " + string);
                }
                LocalDateTime from = LocalDateTime.parse(duration[0].trim(), Bao.fileDateFormat);
                LocalDateTime to = LocalDateTime.parse(duration[1].trim(), Bao.fileDateFormat);
                Event event = new Event(description, from, to);
                if (isDone) {
                    event.mark();
                }
                return event;
            }
            default -> {
                throw new IllegalArgumentException("Bao doesn't know what this task type is");
            }
        }
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        if (isDone) {
            return "1 | " + description;
        } else {
            return "0 | " + description;
        }
    }

    public abstract String toFileString();
}
