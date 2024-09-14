package ynch.ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a description and completion status.
 */

class Task {
    String task;
    boolean isDone;
    static int DAYS_FOR_REMINDER = 5;

    /**
     * Constructs a new Task with the specified description.
     *
     * @param task the description of the task
     */

    Task(String task) {
        this.task = task;
        this.isDone = false;
    }

    /**
     * Constructs a new Task with the specified status and description.
     *
     * @param status the completion status of the task (1 for done, 0 for not done)
     * @param task   the description of the task
     */

    Task(int status, String task) {
        this.task = task;
        this.isDone = (status == 1);
    }

    boolean needsReminder() {
        return false;
    }

    /**
     * Marks the task as done.
     */

    void mark() {
        this.isDone = true;
    }

    /**
     * Unmarks the task, setting its status to not done.
     */


    void unmark() {
        this.isDone = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return the task description
     */

    String getDescription() {
        return this.task;
    }

    /**
     * Returns a string representation of the task, including its completion status.
     *
     * @return a string indicating whether the task is done or not
     */

    public String toString() {
        if (this.isDone) {
            return "[X] " + this.task;
        } else {
            return "[ ] " + this.task;
        }
    }

    /**
     * Returns a string representation of the task suitable for saving to a file.
     *
     * @return a string representing the task's status and description
     */

    String toSaveAsString() {
        if (this.isDone) {
            return "1/" + this.task;
        } else {
            return "0/" + this.task;
        }
    }

    /**
     * Converts a string in the format "yyyy-MM-dd" to a LocalDate object.
     *
     * @param dateString the date string to convert
     * @return the LocalDate representation of the date string, or null if the string is invalid
     */

    LocalDate stringToDate(String dateString) {
        // assume dateString is in valid form yyyy-mm-dd
        dateString = dateString.strip();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate date = LocalDate.parse(dateString, dateFormat);
            return date;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}