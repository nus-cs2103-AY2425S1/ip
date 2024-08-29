package task;

import java.util.ArrayList;

/**
 * List of tasks stored by BotManager
 *
 * @author dwsc37
 */
public class TaskList {
    private final ArrayList<Task> taskList = new ArrayList<>();

    public void addTask(Task task) {
        taskList.add(task);
    }

    public Task markTask(int taskIndex) {
        taskList.get(taskIndex).mark();
        return taskList.get(taskIndex);
    }

    public Task unmarkTask(int taskIndex) {
        taskList.get(taskIndex).unmark();
        return taskList.get(taskIndex);
    }

    public Task deleteTask(int taskIndex) {
        Task task = taskList.get(taskIndex);
        taskList.remove(taskIndex);
        return task;
    }

    public int getTaskCount() {
        return taskList.size();
    }

    public String getSummary() {
        if (taskList.isEmpty()) {
            return "You have no tasks currently.";
        } else if (getTaskCount() == 1) {
            return "You have 1 task currently.";
        }
        return "You have " + getTaskCount()+ " tasks currently.";
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getSummary()).append("\n");
        for (int i = 0; i < getTaskCount(); i++) {
            stringBuilder.append(i + 1).append(". ");
            stringBuilder.append(taskList.get(i).toString()).append("\n");
        }

        return stringBuilder.toString();
    }

    public String toData() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < getTaskCount(); i++) {
            stringBuilder.append(taskList.get(i).toData());
            if (i != getTaskCount() - 1) {
                stringBuilder.append("\n");
            }
        }
        return stringBuilder.toString();
    }

    /**
     * Returns a string all tasks in the the task list which match the given search string.
     *
     * @param  searchString Search string to compare.
     * @return String of all tasks matching the given search string.
     */
    public String findMatches(String searchString) {
        StringBuilder stringBuilder = new StringBuilder();
        int matchCount = 0;
        for (int i = 0; i < getTaskCount(); i++) {
            if (taskList.get(i).isMatch(searchString)) {
                matchCount++;
                stringBuilder.append(i + 1 ).append(". ");
                stringBuilder.append(taskList.get(i).toString()).append("\n");
            }
        }

        if (matchCount == 1) {
            stringBuilder.insert(0, "1 matching task found!\n");
        } else {
            stringBuilder.insert(0, matchCount + " tasks found!\n");
        }

        return stringBuilder.toString();
    }
}
