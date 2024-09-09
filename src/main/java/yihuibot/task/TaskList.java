package yihuibot.task;

import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * A class to store a list of tasks.
 *
 * @author Toh Yi Hui A0259080A
 */
public class TaskList extends ArrayList<Task> {
    /**
     * Constructor for a new TaskList.
     */
    public TaskList() {
        super();
    }

    /**
     * Constructor for a new TaskList using Streams.
     *
     * @param tasks the stream of Tasks.
     */
    public TaskList(Stream<Task> tasks) {
        super();
        tasks.forEach(task -> super.add(task));
    }

    /**
     * Marks the Task at idx as complete.
     *
     * @param idx the 0-index of the Task.
     * @return whether the Task status has changed.
     * @throws IndexOutOfBoundsException when given index is out of bounds.
     */
    public boolean markComplete(int idx) throws IndexOutOfBoundsException {
        return super.get(idx).markComplete();
    }

    /**
     * Marks the Task at idx as incomplete.
     *
     * @param idx the 0-index of the Task.
     * @return whether the Task status has changed.
     * @throws IndexOutOfBoundsException when given index is out of bounds.
     */
    public boolean markIncomplete(int idx) throws IndexOutOfBoundsException {
        return super.get(idx).markIncomplete();
    }

    /**
     * List out the Tasks in TaskList.
     *
     * @return the String representation of listing out the Tasks.
     */
    @Override
    public String toString() {
        if (super.size() < 1) {
            return "";
        }

        String result = super.get(0).toString();
        for (int i = 1; i < super.size(); i++) {
            result += "\n" + super.get(i).toString();
        }

        return result;
    }
}
