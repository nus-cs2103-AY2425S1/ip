package bing.task;

import bing.task.Task;
import bing.task.TaskStatus;

import java.time.format.DateTimeFormatter;
/**
 * Represents a ToDo task.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo task with the given description.
     *
     * @param info the description of the task
     */
    public ToDo(String info) {
        super(info);
    }

    /**
     * Returns the string representation of the ToDo task.
     *
     * @return the ToDo task as a string
     */
    @Override
    public String toString() {
        return "[T]" + "[" + getStatus().getStatusSymbol() + "]" + " " + getInfo();
    }

    /**
     * Returns the ToDo task in a format suitable for saving to a file.
     *
     * @param formatter the DateTimeFormatter (not used for ToDo tasks)
     * @return the ToDo task in file format
     */
    @Override
    public String toFileFormat(DateTimeFormatter formatter) {
        return "T | " + (getStatus() == TaskStatus.DONE ? "1" : "0") + " | " + getInfo();
    }
}