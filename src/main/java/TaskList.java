import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;
    private int taskCount;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.taskCount = tasks.size();
    }

    public void addTask(Task task) {
        tasks.add(task);
        taskCount++;
    }

    public void deleteTask(int index) throws MeowException {
        if (index >= 0 && index < taskCount) {
            tasks.remove(index);
            taskCount--;
        } else {
            throw new MeowException("Invalid task number.");
        }
    }

    public Task getTask(int index) throws MeowException {
        if (index >= 0 && index < taskCount) {
            return tasks.get(index);
        } else {
            throw new MeowException("Invalid task number.");
        }
    }

    public int getTaskCount() {
        return taskCount;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
