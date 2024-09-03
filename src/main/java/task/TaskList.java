package task;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class TaskList implements Iterable<Task> {
    private ArrayList<Task> tasks = new ArrayList<>();

    @Override
    public Iterator<Task> iterator() {
        return tasks.iterator();
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks.addAll(tasks);
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public Task remove(int index) {
        return tasks.remove(index);
    }

    public void markTaskAsDone(int index) {
        tasks.get(index).setIsDone(true);
    }

    public void markTaskAsUndone(int index) {
        tasks.get(index).setIsDone(false);
    }

    public int size() {
        return tasks.size();
    }

    public TaskList findTasks(String s) {
        TaskList filteredTasks = new TaskList();
        for (Task task : tasks) {
            if (task.description.contains(s)) {
                filteredTasks.add(task);
            }
        }
        return filteredTasks;
    }
}