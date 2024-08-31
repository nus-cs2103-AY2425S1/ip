import java.util.ArrayList;

public class Tasklist {

    private ArrayList<Task> tasksList;

    public Tasklist(Storage storage) {
        this.tasksList = storage.getTaskLists();
    }

    public void add(Task task) {
        tasksList.add(task);
    }

    public Task get(int index) {
        return tasksList.get(index);
    }

    public Task remove(int index) {
        return tasksList.remove(index);
    }

    public void clear() {
        tasksList.clear();
    }

    public int size() {
        return tasksList.size();
    }

    public void save(Storage storage) {
        storage.saveTaskList(tasksList);
    }

}
