import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public int getSize() {
        return tasks.size();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void markTask(int index) throws DeltaException {
        if (tasks.isEmpty()) {
            throw new DeltaException("OOPS!!! List is empty, there is no task to mark.");
        } else if (index < 1 || index > tasks.size()) {
            throw new DeltaException("""
                    OOPS!!! Task not found in list.
                    \t Please provide a valid Task to mark.""");
        }
        Task task = tasks.get(index - 1);
        if (task.getStatusIcon().equals("X")) {
            throw new DeltaException("OOPS!!! Task is already marked as done.");
        }
        task.markAsDone();
        tasks.set(index - 1, task);
    }

    public void unmarkTask(int index) throws DeltaException {
        if (tasks.isEmpty()) {
            throw new DeltaException("OOPS!!! List is empty, there is no task to unmark.");
        } else if (index < 1 || index > tasks.size()) {
            throw new DeltaException("""
                    OOPS!!! Task not found in list.
                    \t Please provide a valid Task to unmark.""");
        }
        Task task = tasks.get(index - 1);
        if (task.getStatusIcon().equals(" ")) {
            throw new DeltaException("OOPS!!! Task is already marked as not done yet.");
        }
        task.markAsNotDone();
        tasks.set(index - 1, task);
    }

    public void deleteTask(int index) throws DeltaException {
        if (tasks.isEmpty()) {
            throw new DeltaException("OOPS!!! List is empty, there is no task to delete.");
        } else if (index < 1 || index > tasks.size()) {
            throw new DeltaException("""
                    OOPS!!! Task not found in list.
                    \t Please provide a valid Task to delete.""");
        }
        tasks.remove(index - 1);
    }
}
