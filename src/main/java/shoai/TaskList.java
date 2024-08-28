package shoai;
import shoai.Task; // Imports the Task class for task management
import shoai.ShoAIException; // Imports the ShoAIException class for handling exceptions
import java.util.ArrayList; // Imports ArrayList for task storage

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

    public int size() {
        return tasks.size();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task getTask(int index) throws ShoAIException {
        if (index < 0 || index >= tasks.size()) {
            throw new ShoAIException("Task number out of range.");
        }
        return tasks.get(index);
    }

    public Task removeTask(int index) throws ShoAIException {
        if (index < 0 || index >= tasks.size()) {
            throw new ShoAIException("Task number out of range.");
        }
        return tasks.remove(index);
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Finds tasks that contain the specified keyword.
     *
     * @param keyword The keyword to search for.
     * @return A list of tasks that contain the keyword.
     */
    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.toString().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}
