package skibidi;

import java.util.List;

import skibidi.task.AbstractTask;

/**
 * Represents a list of Tasks for the application.
 */
public class TaskList {
    /**
     * Exception thrown when a task with given number id is not found in TaskList.
     */
    public static class TaskNotFoundException extends Exception {
        public TaskNotFoundException() {
            super("TASK WITH ITEM NUMBER NOT FOUND");
        }
    }

    private final List<AbstractTask> tasks;

    public TaskList(List<AbstractTask> tasks) {
        this.tasks = tasks;
    }

    /**
     * Return whether task list is empty.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Return number of items in task list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Return whether task with number id is present in the task list.
     */
    public boolean hasTaskWithId(int taskId) {
        return taskId > 0 && taskId <= tasks.size();
    }

    /**
     * Return Task with given number id.
     */
    public AbstractTask getTask(int taskId) throws TaskNotFoundException {
        if (hasTaskWithId(taskId)) {
            return tasks.get(taskId - 1);
        } else {
            throw new TaskNotFoundException();
        }
    }

    /**
     * Add task into task list.
     */
    public void addTask(AbstractTask task) {
        assert task != null : "Task being added is invalid!";
        tasks.add(task);
    }

    /**
     * Delete task with given number id from the task list.
     */
    public AbstractTask deleteTask(int taskId) throws TaskNotFoundException {
        if (hasTaskWithId(taskId)) {
            return tasks.remove(taskId - 1);
        } else {
            throw new TaskNotFoundException();
        }
    }

    /**
     * Return new list of tasks with description that matches given string.
     */
    public List<AbstractTask> findTasksMatchingDescription(String query) {
        return tasks.stream()
                .filter(task -> task.getDescription().contains(query))
                .toList();
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
