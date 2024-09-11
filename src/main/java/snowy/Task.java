package snowy;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;


/**
 * Represents an abstract Task with all the methods that a Task should do.
 */
public abstract class Task {
    private boolean isCompleted = false;

    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG);

    private final String name;

    public Task(String name) {
        this.name = name;
    }

    /**
     * Marks the task as complete.
     */
    public void markComplete() {
        this.isCompleted = true;
    }

    /**
     * Marks the task as incomplete.
     */
    public void markIncomplete() {
        this.isCompleted = false;
    }

    /**
     * Convert the task into a String that will be saved into a file.
     * @return the String that represents the task when saved.
     */
    public String toFileStorage() {
        String completeString = isCompleted ? "1" : "0";
        return completeString + "|" + name;
    }
    @Override
    public String toString() {
        String completeIcon = isCompleted ? "X" : " ";
        return "[" + completeIcon + "] " + this.name;
    }

    public String getName() {
        return this.name;
    }
}
