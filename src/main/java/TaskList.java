import java.util.ArrayList;

// TaskList class encapsulates a task list tracked by Bro Bot
public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public Task addTask(Task task) {
        tasks.add(task);
        return task;
    }

    public Task markTask(int taskId) throws Exception {
        Task task = tasks.get(taskId - 1);
        task.markTask();
        return task;
    }

    public Task unmarkTask(int taskId) throws Exception {
        Task task = tasks.get(taskId - 1);
        task.unmarkTask();
        return task;
    }

    public void printAllTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    public int getNumberOfTask() {
        return tasks.size();
    }
}
