import java.util.ArrayList;
/**
 * Represents a list of tasks.
 * This class manages a collection of tasks and provides methods to add and list them.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs an empty task list.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone(int index) {
        Task task = tasks.get(index);
        task.done();
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone(int index) {
        Task task = tasks.get(index);
        task.notDone();
    }

    /**
     * Returns a string representation of the task list.
     * Each task is represented with an index and a description.
     *
     * @return A string listing all tasks with their indices.
     */
    @Override
    public String toString() {
        String listOfTasks = "";
        for (int i = 0; i < tasks.size(); i++) {
            listOfTasks += (i + 1) + ". " + tasks.get(i) + "\n";
        }
        return listOfTasks;

    }
}


