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

    public void checkTask(int index) throws TaskListIndexOutOfBoundsException {
        // checks if there is a task at the given index
        // if not throw TaskListIndexOutOfBoundsException
        if (index < 0 || index >= tasks.size()) {
            String message = String.format(
                    """
                            Whats this? You tried an invalid list index!?
                            Pfft... That's so hilarious!
                            Lemme spell it out for ya.
                            Your number must be between 1 and %d and clearly %d isn't""",
                    tasks.size(),
                    index + 1);
            throw new TaskListIndexOutOfBoundsException(message);
        }
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task getTask(int index) throws TaskListIndexOutOfBoundsException {
        checkTask(index);
        return tasks.get(index);
    }

    public void markComplete(int index) throws TaskListIndexOutOfBoundsException {
        checkTask(index);
        tasks.get(index).markAsDone();
    }

    public void markIncomplete(int index) throws TaskListIndexOutOfBoundsException {
        checkTask(index);
        tasks.get(index).markAsUndone();
    }
}
