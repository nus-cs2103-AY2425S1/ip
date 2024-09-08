package shnoop.tasks;

import shnoop.exceptions.*;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates the overall Task that all sub-tasks and types of tasks inherit from.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a Task.
     *
     * @param description
     */
    public Task(String description){
        if (description.isEmpty()) {
            try {
                throw new EmptyDescriptionException();
            } catch (EmptyDescriptionException e) {
                System.out.println("This task ain't it pal.");
            }
        }
        this.description = description;
        isDone = false;
    }

    /**
     * Returns String representation of Task's mark status.
     *
     * @return String representation of Task including its mark status. "X" if it is marked, and " " if it is not.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns Task description.
     *
     * @return String value of description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns String representation of the Task along with whether it is marked.
     *
     * @return String representation of Task including its mark status.
     */
    public String getTaskWithStatus() {
        return String.format("[%s] %s", getStatusIcon(), getDescription());
    }

    /**
     * Sets task as done.
     *
     * @return True if task completion status changed.
     */
    public boolean markTask() {
        if (!isDone) {
            isDone = true;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Sets task as undone.
     *
     * @return true if task completion status changed.
     */
    public boolean unmarkTask() {
        if (isDone) {
            isDone = false;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return getTaskWithStatus();
    }

    /**
     * Converts all the Task's information into a unique style to be written into a txt file.
     * 
     * @return String which encompasses all the relevant Task information that can be deciphered and read later.
     */
    public String toUniqueFileString() {
        String s = "";
        s += isDone ? "1" : "0";
        return s;
    }

    /**
     * Converts any valid String representing a valid Java LocalDate into its String counterpart.
     *
     * @param string Input to be read and analysed.
     * @return New String representation of the date if input was a valid Java LocalDate, otherwise return input.
     */
    public String parseDate(String string) {
        // YYYY-MM-DD
        if (string.length() != 10) {
            System.out.println("Well this happened BUG TESTING OUTPUT: " + string.length() + " for " + string);
            return string;
        }

        try {
            LocalDate d = LocalDate.parse(string
                    .replaceAll("/", "-"));
            String s = d.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            return s;
        } catch (DateTimeException e) {
            System.out.println("This was not a valid date format");
            return string;
        }
    }
}
