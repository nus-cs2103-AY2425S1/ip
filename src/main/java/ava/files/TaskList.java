package ava.files;

import ava.task.Task;

import java.util.ArrayList;

public class TaskList extends ArrayList<Task> {
    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        for (int i = 1; i <= size(); i++) {
            out.append(i);
            out.append(". ");
            out.append(get(i - 1));
            out.append("\n");
        }
        return out.toString();
    }
}
