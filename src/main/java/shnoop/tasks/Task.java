package shnoop.tasks;

import shnoop.exceptions.*;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Task {
    protected String description;
    protected boolean isDone;

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

    public Task() {
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getDescription() {
        return description;
    }

    public String getTaskWithStatus() {
        return String.format("[%s] %s", getStatusIcon(), getDescription());
    }

    /**
     * Sets task as done.
     *
     * @return true if task completion status changed.
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
     * Returns unique ID style to be read into txt file.
     * 
     * @return String indicating Task's details per chosen style.
     */
    public String toUString() {
        String s = "";
        s += isDone ? "1" : "0";
        return s;
    }

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
