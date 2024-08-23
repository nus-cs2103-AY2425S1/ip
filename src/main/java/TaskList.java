import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TaskList implements Iterable<Task> {
    private final List<Task> taskList;
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(List<Task> storage) {
        this.taskList = storage;
    }

    public void add(Task t) {
        taskList.add(t);
    }
    public Task delete(int index) {
        return taskList.remove(index);
    }

    public boolean isEmpty() {
        return taskList.isEmpty();
    }

    public Task get(int i) {
        return taskList.get(i);
    }

    public int indexOf(Object o) {
        return taskList.indexOf(o);
    }

    public int size() {
        return taskList.size();
    }

    @Override
    public Iterator<Task> iterator() {
        return taskList.iterator();  // Returns an iterator for the taskList
    }
}
