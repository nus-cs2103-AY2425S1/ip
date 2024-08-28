package task;

import java.util.ArrayList;

/**
 * List of tasks stored by BotManager
 *
 * @author dwsc37
 */
public class TaskList {
    private final ArrayList<Task> tasks = new ArrayList<>();

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task markTask(int taskIndex) {
        tasks.get(taskIndex).mark();
        return tasks.get(taskIndex);
    }

    public Task unmarkTask(int taskIndex) {
        tasks.get(taskIndex).unmark();
        return tasks.get(taskIndex);
    }

    public Task deleteTask(int taskIndex) {
        Task task = tasks.get(taskIndex);
        tasks.remove(taskIndex);
        return task;
    }

    public int getTaskCount() {
        return tasks.size();
    }

    public String getSummary() {
        if (tasks.isEmpty()) {
            return "You have no tasks currently.";
        } else if (getTaskCount() == 1) {
            return "You have 1 task currently.";
        }
        return "You have " + getTaskCount() + " tasks currently.";
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getSummary()).append("\n");
        for (int i = 0; i < getTaskCount(); i++) {
            stringBuilder.append(i + 1).append(". ");
            stringBuilder.append(tasks.get(i).toString()).append("\n");
        }

        return stringBuilder.toString();
    }

    public String toData() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < getTaskCount(); i++) {
            stringBuilder.append(tasks.get(i).toData());
            if (i != getTaskCount() - 1) {
                stringBuilder.append("\n");
            }
        }
        return stringBuilder.toString();
    }
}
