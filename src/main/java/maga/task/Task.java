package maga.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import maga.exceptions.LoadTaskException;

/**
 * An abstract class representing a task. A task has a description and a completion status.
 * The specific type of task is determined by subclasses.
 */
public abstract class Task {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final Integer TODOLENGTH = 4;
    private static final Integer EVENTLENGTH = 5;
    private static final Integer DEADLINELENGTH = 6;
    private String tag = "";
    protected String description;
    protected boolean isDone;


    /**
     * Constructs a {@code Task} with the given description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }


    /**
     * Returns the status icon of the task. Indicates whether the task is completed or not.
     *
     * @return A string representing the status icon: "[X]" if done, "[ ]" if not done.
     */
    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] "); // mark done maga.task with X
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void markAsUndone() {
        isDone = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return A string of the description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the type of the task. The specific type is determined by subclasses.
     *
     * @return A string representing the type of the task.
     */
    public abstract String getTaskType();

    /**
     * Returns a string representation of the task, including its type, status, and description.
     *
     * @return A string representation of the task.
     */
    public abstract String printTask();

    /**
     * Creates a {@code Task} object from a string representation of the task.
     * The string format is: "type | status | description | date | tag".
     * Only called when parsing string representation of tasks from the Maga data file by {@code TaskManager}.
     *
     * @param taskString The string representation of the task.
     * @return A {@code Task} object created from the string, or a default {@code TodoTask} if string cannot be parsed.
     */
    public static Task fromString(String taskString) throws LoadTaskException {
        String[] parts = taskString.split(" \\| ");
        try {
            if (parts.length == TODOLENGTH) {
                boolean isDone = parts[1].equals("1");
                String description = parts[2];
                Task temp = new TodoTask(isDone, description);
                temp.setTag(parts[3]);
                return temp;
            } else if (parts.length == EVENTLENGTH) {
                boolean isDone = parts[1].equals("1");
                String description = parts[2];
                LocalDate dateTime = LocalDate.parse(parts[3], FORMATTER);
                Task temp = new EventTask(isDone, description, dateTime);
                temp.setTag(parts[4]);
                return temp;
            } else if (parts.length == DEADLINELENGTH) {
                boolean isDone = parts[1].equals("1");
                String description = parts[2];
                LocalDate startDate = LocalDate.parse(parts[3], FORMATTER);
                LocalDate endDate = LocalDate.parse(parts[4], FORMATTER);
                Task temp = new DeadlineTask(isDone, description, startDate, endDate);
                temp.setTag(parts[5]);
                return temp;
            }
        } catch (DateTimeParseException e) {
            throw new LoadTaskException();
        }

        return new TodoTask(false, "");
    }

    /**
     * Returns a string representation of the task. This method must be implemented by subclasses.
     *
     * @return A string representation of the task.
     */
    @Override
    public abstract String toString();

    /**
     * Sets the {@code tag} field of the task to the input.
     *
     * @param tag A string to tag to the task.
     */
    public void setTag(String tag) {
        this.tag = tag;
    }

    /**
     * Retrieves the {@code tag} field of the task.
     *
     * @return A string representation of the task's tag.
     */
    public String getTag() {
        return tag;
    }
}
