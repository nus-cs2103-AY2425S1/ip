package cow.tasks;

import java.time.LocalDate;

/**
 * Represents a Task object.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a Task object.
     *
     * @param description The Description of the Tasks.Task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Creates a Task object.
     *
     * @param isDone      The status of the Tasks.Task
     * @param description The Description of the Tasks.Task
     */
    public Task(String isDone, String description) {
        this.description = description;
        this.isDone = isDone.equals("1");
    }

    /**
     * Returns the status of the Task.
     *
     * @return The status of the Tasks.
     */
    public String getStatusIcon() {
        return (this.isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the todo object as completed
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Unmarks the todo object as completed
     */
    public void unmarkAsDone() {
        this.isDone = false;
    }

    /**
     * Returns the string representation of the Task.
     *
     * @return The string representation of the Task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    /**
     * Returns the date of the Task.
     * For generic tasks, this returns a default date.
     *
     * @return The date of the Task.
     */
    public LocalDate getDate() {
        return LocalDate.of(0, 1, 1);
    }

    /**
     * Checks if the task description contains the specified substring.
     *
     * @param subString The substring to check for.
     * @return True if the description contains the substring, false otherwise.
     */
    public boolean getContainsSubString(String subString) {
        return this.description.contains(subString);
    }

    /**
     * Returns the save data of the Task.
     *
     * @return The save data of the Task.
     */
    public String getSaveData() {
        String result = "";
        if (this.isDone) {
            result += "1";
        } else {
            result += "0";
        }
        return result + " | " + this.description;
    }
}
