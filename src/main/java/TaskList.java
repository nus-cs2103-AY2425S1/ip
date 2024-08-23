import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int index) {
        return tasks.get(index - 1);
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    public void delete(int index) {
        this.tasks.remove(index - 1);
    }

}
