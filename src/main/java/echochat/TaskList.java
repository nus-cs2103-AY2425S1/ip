package echochat;
import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> lst = new ArrayList<>();
    private Storage storage = new Storage();

    /**
     * Save tasks to file using Storage.
     */
    public void saveTasksToFile() {
        storage.save(lst);
    }

    /**
     * Loads tasks from saved file using Storage.
     */
    public void loadTasksFromFile() {
        lst = storage.load();
    }

    public ArrayList<Task> getTaskList() {
        return lst;
    }

    public void addToList(Task task){
        try {
            lst.add(task);
            saveTasksToFile();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Task delete(int i) {
        Task task = lst.remove(i - 1);
        saveTasksToFile();
        return task;
    }
}
