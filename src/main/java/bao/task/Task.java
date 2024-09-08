package bao.task;

import java.time.LocalDateTime;

import bao.main.Bao;

/**
 * The Task class represents a task with a description and a completion status.
 * It is an abstract class that provides methods applicable for all types of tasks.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new Task with the given description and sets it as not done.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Creates a Task object from a string input.
     * The string should be formatted in a specific way to be parsed and for the task to be created correctly.
     *
     * @param string String representation of the task.
     * @return Task object created from the string.
     * @throws IllegalArgumentException Ff the string format is invalid or unknown task type is provided.
     */
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
            ToDo todo = new ToDo(description);
            if (isDone) {
                todo.mark();
            }
            return todo;
        }
        case "D" -> {
            if (parts.length < 4) {
                throw new IllegalArgumentException("Invalid deadline task format: " + string);
            }
            LocalDateTime dateAndTime = LocalDateTime.parse(parts[3].trim(), Bao.getFileDateFormat());
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
            LocalDateTime from = LocalDateTime.parse(duration[0].trim(), Bao.getFileDateFormat());
            LocalDateTime to = LocalDateTime.parse(duration[1].trim(), Bao.getFileDateFormat());
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

    /**
     * Marks the task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Un-arks the task as not done.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return (isDone ? "1 | " : "0 | ") + description.trim();
    }

    /**
     * Returns the string representation of the task to be saved to a file.
     * This method must be implemented by all subclasses.
     *
     * @return String representation of the task to be saved to a file.
     */
    public abstract String toFileString();
}
