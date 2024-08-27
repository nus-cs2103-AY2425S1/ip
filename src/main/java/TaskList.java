import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task deleteTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            return tasks.remove(index);
        }
        return null;
    }

    public boolean markTaskAsDone(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).markAsDone();
            return true;
        }
        return false;
    }

    public boolean markTaskAsNotDone(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).markAsNotDone();
            return true;
        }
        return false;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

//    public ArrayList<Task> getLast() {
//        if(tasks.size() != 0) {
//            return tasks.getLast();
//        }
//        return null;
//    }
    public Task getTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            return tasks.get(index);
        }
        return null;
    }

    public int size() {
        return tasks.size();
    }
}
