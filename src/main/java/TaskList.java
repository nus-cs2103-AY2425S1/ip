import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> items = new ArrayList<>();

    public TaskList(ArrayList<Task> items) {
        this.items = items;
    }

    public Task get(int i) {
        return items.get(i);
    }

    public int size() {
        return items.size();
    }

    public void add(Task task) {
        items.add(task);
    }

    public void mark(int i) {
        Task task = items.get(i-1);
        task.complete();
    }

    public void unmark(int i) {
        Task task = items.get(i-1);
        task.undo();
    }

    public void delete(int i) {
        items.remove(i - 1);
    }
}
