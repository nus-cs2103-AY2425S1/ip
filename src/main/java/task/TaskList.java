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

    public int getTaskCount() {
        return taskList.size();
    }

    @Override
    public String toString() {
        if (taskList.isEmpty()) {
            return "You have no tasks currently";
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("This is your current list of tasks:\n");
        for (int i = 0; i < getTaskCount(); i++) {
            stringBuilder.append(i + 1);
            stringBuilder.append(". ");
            stringBuilder.append(taskList.get(i).toString());

            if (i != getTaskCount()) {
                stringBuilder.append("\n");
            }
        }

        return stringBuilder.toString();
    }
}
