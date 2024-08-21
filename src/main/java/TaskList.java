import java.util.ArrayList;
import java.util.Iterator;

public class TaskList implements Iterable<Task> {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public int getIncompleteCount() {
        int count = 0;
        for (Task task: tasks) {
            if (!task.isDone()) {
                count++;
            }
        }
        return count;
    }

    @Override
    public Iterator<Task> iterator() {
        return tasks.iterator();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task getTask(int index) {
        // assumes valid index
        return tasks.get(index);
    }

    public void markComplete(int index) {
        // assumes valid index
        tasks.get(index).markAsDone();
    }

    public void markIncomplete(int index) {
        // assumes valid index
        tasks.get(index).markAsUndone();
    }
}
