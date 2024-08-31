package weeny;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int index) {
        tasks.remove(index);
    }

    public void markTask(int index) {
        tasks.get(index).setMark();
    }

    public void unmarkTask(int index) {
        tasks.get(index).setUnmark();
    }

    public ArrayList<Task> findTask(String keyWord) {
        Iterator<Task> itr = tasks.iterator();
        ArrayList<Task> searchResult = new ArrayList<>();
        while (itr.hasNext()) {
            Task task = itr.next();
            if (task.containString(keyWord)) {
                searchResult.add(task);
            }
        }
        return searchResult;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public int size() {
        return tasks.size();
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }
}
