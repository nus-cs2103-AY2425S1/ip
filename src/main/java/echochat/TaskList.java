package echochat;
import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskList = new ArrayList<>();
    private Storage storage = new Storage();

    /**
     * Save tasks to file using Storage.
     */
    public void saveTasksToFile() {
        storage.save(taskList);
    }

    /**
     * Loads tasks from saved file using Storage.
     */
    public void loadTasksFromFile() {
        taskList = storage.load();
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public void addToList(Task task){
        try {
            taskList.add(task);
            saveTasksToFile();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Deletes task from tasklist.
     *
     * @param idx The index of the task to be removed
     * @return The deleted task
     */
    public Task delete(int idx) {
        Task task = taskList.remove(idx - 1);
        saveTasksToFile();
        return task;
    }
}
