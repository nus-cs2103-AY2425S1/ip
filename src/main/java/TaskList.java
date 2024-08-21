import java.util.ArrayList;

public class TaskList {

    private final ArrayList<Task> tasks;
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Add a task to the end of the list
     * @param task Task object to be added
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Mark a task as done
     * @param taskId Index of the task to be marked
     */
    public void mark(int taskId) {
        tasks.get(taskId - 1).markDone();
    }

    /**
     * Mark a task as not done
     * @param taskId Index of the task to be unmarked
     */
    public void unmark(int taskId) {
        tasks.get(taskId - 1).markNotDone();
    }

    /**
     *
     * @param taskId
     * @return
     */
    public String getTaskStatus(int taskId) {
        return tasks.get(taskId - 1).toString();
    }

    /**
     * Get the status of the last added task
     * @return String representation of the task
     */
    public String getLatestTask() {
        if (tasks.isEmpty()) {
            return "";
        }
        return tasks.get(tasks.size() - 1).toString();
    }


    /**
     * Get the total number of tasks
     * @return Count of all marked and unmarked tasks
     */
    public int getTaskCount() {
        return tasks.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int nTasks = getTaskCount();
        for (int index = 0; index < nTasks; index++) {
            sb.append(String.format("%d: %s\n", index + 1, tasks.get(index)));
        }
        return "Here are the tasks in your list:\n" + sb.toString();
    }
}
