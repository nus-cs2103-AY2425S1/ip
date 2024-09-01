package Tasks;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> TASKS;

    public TaskList(ArrayList<Task> tasks) {
        this.TASKS = tasks;
    }

    // Helper function to count number of tasks currently
    public int numTasks() {
        int count = 0;
        for (Task task : TASKS) {
            if (task != null) {
                count += 1;
            } else {
                break;
            }
        }
        return count;
    }

    public void markTask(int targetIndex) {
        Task task = TASKS.get(targetIndex);
        task.markAsDone();
    }

    public void unmarkTask(int targetIndex) {
        Task targetTask = TASKS.get(targetIndex);
        targetTask.markAsUndone();
    }

    public void addToList(Task task) {
        TASKS.add(task);
    }

    public void removeFromList(int targetIndex) {
        TASKS.remove(targetIndex);
    }

    public Task getTask(int targetIndex) {
        return TASKS.get(targetIndex);
    }

    // Helper function to get task details as a string
    public String getTaskDetails(Task task) {
        return "[" + task.getTaskType() + "]"
                + "[" + task.getStatusIcon() + "] "
                + task.getDescription()
                + task.getExtraInfo();
    }

    public String getTaskDetails(int targetIndex) {
        Task task = this.getTask(targetIndex);
        return this.getTaskDetails(task);
    }

    // Helper function to get task summary, detailing current number of tasks
    public String getTasksSummary() {
        int totalTasks = this.numTasks();
        return "Now you have "
                + totalTasks
                + (totalTasks == 1
                ? " task in the list."
                : " tasks in the list.");
    }
}
