import java.util.ArrayList;

// TaskList class encapsulates a task list tracked by Bro Bot
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public void addTask(String taskContent) {
        tasks.add(new Task(taskContent));
    }

    public void markTask(int taskId) {
        try {
            Task task = tasks.get(taskId);
            task.markTask();
        } catch (IndexOutOfBoundsException e) {
            return;
        }
    }

    public void unmarkTask(int taskId) {
        try {
            Task task = tasks.get(taskId);
            task.unmarkTask();
        } catch (IndexOutOfBoundsException e) {
            return;
        }
    }

    public void printAllTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }
}
