package ava.task;

import java.util.ArrayList;
import java.util.List;

/**
 * Creates a class specialized for storing Tasks
 *
 * @inheritDoc
 */
public class TaskList extends ArrayList<Task> {

    public TaskList() {
        super();
    }

    public TaskList(List<Task> tasks) {
        super(tasks);
    }

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
