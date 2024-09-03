package neon;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Task {
    private String name;
    private boolean isCompleted;

    /**
     * Constructs a Task object with the specified description.
     *
     * @param name - Description of task.
     * @param isCompleted - Whether the task is completed.
     */
    public Task(String name, boolean isCompleted) {
        this.name = name;
        this.isCompleted = isCompleted;
    }

    /**
     * Returns a String reflecting whether the task is completed.
     *
     * @return String reflecting completeness.
     */
    public String checkMark() {
        if (isCompleted) {
            return "X";
        } else {
            return " ";
        }
    }

    /**
     * Returns a String reflecting whether the task is completed.
     *
     * @return String reflecting completeness.
     */
    public String completeStatus() {
        if (isCompleted) {
            return "1";
        } else {
            return "0";
        }
    }

    /**
     * Formats a date of a specific format into a new format.
     *
     * @param date - a String that represents a date.
     * @return String that represents a date.
     * @throws DateTimeException when the date time does not follow a specific format.
     */
    public String processDate(String date) throws DateTimeException {
        try {
            LocalDate parsedDate = LocalDate.parse(date);
            return parsedDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } catch (DateTimeException d) {
            return date;
        }
    }

    /**
     * Assign the value of true into isCompleted.
     */
    public void check() {
        this.isCompleted = true;
    }

    /**
     * Assign the value of false into isCompleted.
     */
    public void uncheck() {
        this.isCompleted = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return String description of task.
     */
    public String getName() {
        return this.name;
    }

    @Override
    public abstract String toString();

    public abstract String toTask();
}
