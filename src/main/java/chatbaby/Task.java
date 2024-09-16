package chatbaby;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a name and completion status.
 * Subclasses define specific types of tasks (e.g., ToDo, Deadline, Event).
 */
public abstract class Task {
    private String name;
    private boolean isDone;

    /**
     * Constructs a Task with the specified name. The task is initially marked as not done.
     *
     * @param name The name of the task.
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Converts the task to a string format suitable for saving to a file.
     *
     * @return A string representation of the task in file format.
     */
    public abstract String toFileText();

    /**
     * Creates a Task object from a string formatted for file storage.
     *
     * @param fileFormat The string representation of the task in file format.
     * @return The Task object corresponding to the string.
     * @throws Exception If the task type is invalid or if parsing fails.
     */
    public static Task fromFileText(String fileFormat) throws Exception {
        String[] text = fileFormat.split(" \\| ");
        String taskType = text[0];
        boolean isDone = text[1].equals("1");
        String name = text[2];

        Task task;
        switch (taskType) {
        case "T" -> task = new ToDo(name);
        case "D" -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
            LocalDateTime deadline = LocalDateTime.parse(text[3], formatter);
            task = new Deadline(name, deadline);
        }
        case "E" -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
            LocalDateTime from = LocalDateTime.parse(text[3], formatter);
            LocalDateTime to = LocalDateTime.parse(text[4], formatter);
            task = new Event(name, from, to);
        }
        default -> throw new Exception("Invalid task type");
        }

        if (isDone) {
            task.markAsDone();
        }
        return task;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unMarkAsDone() {
        isDone = false;
    }
    public String getName() {
        return this.name;
    }
    public boolean isDone() {
        return isDone;
    }

    /**
     * Checks if the task is on a specific date.
     *
     * @param date The date to check.
     * @return True if the task is on the specified date, otherwise false.
     */
    public boolean isOnDate(LocalDate date) {
        if (this instanceof Deadline deadlineTask) {
            return deadlineTask.getDeadline().toLocalDate().equals(date);
        } else if (this instanceof Event eventTask) {
            return eventTask.getEventEndTime().toLocalDate().equals(date);
        }
        return false;
    }

    /**
     * Returns a string representation of the task for display.
     *
     * @return A string indicating the task's completion status and name.
     */
    @Override
    public String toString() {
        return isDone ? "[X] " + name : "[ ] " + name;
    }
}
