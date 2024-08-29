package skibidi;

import java.util.ArrayList;
import java.util.List;

import skibidi.task.AbstractTask;

public class TaskList {
    public static class TaskNotFoundException extends Exception {
        public TaskNotFoundException() {
            super("TASK WITH ITEM NUMBER NOT FOUND");
        }
    }

    private final List<AbstractTask> tasks;

    public TaskList(List<AbstractTask> tasks) {
        this.tasks = tasks;
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public int size() {
        return tasks.size();
    }

    public boolean hasTaskWithId(int taskId) {
        return taskId > 0 && taskId <= tasks.size();
    }

    public AbstractTask getTask(int taskId) throws TaskNotFoundException {
        if (hasTaskWithId(taskId)) {
            return tasks.get(taskId - 1);
        } else {
            throw new TaskNotFoundException();
        }
    }

    public void addTask(AbstractTask task) {
        tasks.add(task);
    }

    public AbstractTask deleteTask(int taskId) throws TaskNotFoundException {
        if (hasTaskWithId(taskId)) {
            return tasks.remove(taskId - 1);
        } else {
            throw new TaskNotFoundException();
        }
    }

    public TaskList findTasksMatchingDescription(String query) {
        List<AbstractTask> results = new ArrayList<>();
        for (AbstractTask task : tasks) {
            if (task.getDescription().contains(query)) {
                results.add(task);
            }
        }
        return new TaskList(results);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            stringBuilder.append(String.format("\t%d. ", i + 1));
            stringBuilder.append(tasks.get(i).toString());
            if (i < tasks.size() - 1) {
                stringBuilder.append("\n");
            }
        }
        return stringBuilder.toString();
    }
}
