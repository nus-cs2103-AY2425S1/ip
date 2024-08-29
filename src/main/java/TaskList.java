import java.util.ArrayList;

public class TaskList  {
    private ArrayList<Task> tasks = new ArrayList<Task>();

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public Task removeTask(int i) {
        Task task = tasks.get(i);
        this.tasks.remove(i);

        return task;
    }

    public void markTaskAsDone(int i) {
        tasks.get(i).markDone();
    }

    public void unmarkTaskAsDone(int i) {
        tasks.get(i).unmarkDone();
    }

    public int noOfTasks() {
        return tasks.size();
    }

    public String taskAtIndexToString(int i) {
        return tasks.get(i).toString();
    }
}
