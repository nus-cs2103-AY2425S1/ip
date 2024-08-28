import java.util.ArrayList;

public class TaskList {

    ArrayList<Task> tasks;

    TaskList(ArrayList<Task> _tasks) {
        tasks = _tasks;
    }

    public Task remove(int index) {
        return tasks.remove(index);
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public boolean add(Task task) {
        return tasks.add(task);
    }

    public Task getLast(int index) {
        return tasks.get(tasks.size() -1);
    }

    public ArrayList<Task> getList() {
        return tasks;
    }

    public int size() {
        return tasks.size();
    }

}
