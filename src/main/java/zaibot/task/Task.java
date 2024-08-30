package zaibot.task;

import java.time.format.DateTimeFormatter;

/**
 * This class represents a task.
 */
public class Task {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHSS");
    private String name;
    private boolean isDone;

    /**
     * Creates a task, given the name
     * @param name A string representing the name
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * This gets the formatter, which will be used for input for all the tasks if
     * they have a date portion
     *
     * @return A DateTimeFormatter with the pattern currently chosen.
     */
    public static DateTimeFormatter getFormatter() {
        return formatter;
    }

    /**
     * Sets the completion status of the task.
     *
     * @param status The status to change to
     */
    public void setDone(boolean status) {
        this.isDone = status;
    }

    /**
     * Checks if the name contains the input
     *
     * @param input Any string
     * @return true if it contains the input, false otherwise.
     */
    public boolean containsInput(String input) {
        return this.name.contains(input);
    }

    /**
     * Returns the string representation of the task when saved.
     *
     * @return a string in the format TYPE | COMPLETION \ NAME | ....
     */
    public String toSaveString() {
        return String.format("%s | %s",
                this.isDone ? "COMPLETE" : "INCOMPLETE",
                this.name);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s",
                isDone ? "X" : " ",
                name);
    }

}
