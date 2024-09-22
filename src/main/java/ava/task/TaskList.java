package ava.task;

import java.util.ArrayList;
import java.util.List;

/**
 * Creates a class specialized for storing Tasks
 *
 * @inheritDoc
 */
public class TaskList extends ArrayList<Task> {

    /**
     * Creates an empty TaskList.
     */
    public TaskList() {
        super();
    }

    /**
     * Creates a TaskList with the given tasks.
     *
     * @param tasks the tasks to be added to the TaskList.
     */
    public TaskList(List<Task> tasks) {
        super(tasks);
    }

    /**
     * Converts the List to a displayable format.
     *
     * @return the tasks in the form of a list.
     */
    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        if (size() == 0) {
            return "No tasks found!";
        }
        for (int i = 1; i <= size(); i++) {
            out.append(i);
            out.append(". ");
            out.append(get(i - 1));
            out.append("\n");
        }
        return out.toString();
    }
}
