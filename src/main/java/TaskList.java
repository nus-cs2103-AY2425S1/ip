import java.io.Serializable;
import java.util.ArrayList;

public class TaskList implements Serializable {

    private static final long serialVersionUID = 1L;
    protected ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public Task get(int i) {
        return tasks.get(i);
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public void remove(int index) {
        Task task = this.tasks.get(index);
        this.tasks.remove(task);
    }

    public void remove (Task t) {
        this.tasks.remove(t);
    }

    public boolean isEmpty() {
        return tasks == null || tasks.isEmpty();
    }

    public int size() {
        return tasks == null ? 0 : tasks.size();
    }

}
