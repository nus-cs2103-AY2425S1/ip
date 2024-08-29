import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    private static int length = 0;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        length = tasks.size();
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        this.tasks.add(task);
        length += 1;
    }

    public Task removeTask(int i) {
        Task task = this.tasks.remove(i);
        length -= 1;
        return task;
    }

    public int getLength() {
        return length;
    }

    public Task getTask(int i) {
        return this.tasks.get(i);
    }
}
