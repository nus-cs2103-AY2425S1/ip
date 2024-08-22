import java.util.ArrayList;
import java.util.List;

public class TaskList {
    protected ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public void displayList() {
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println("\t" + (i + 1) + "." + this.taskList.get(i));
        }
    }

    public Task get(int index) {
        return this.taskList.get(index);
    }

    public void add(Task task) {
        this.taskList.add(task);
    }

    public int size() {
        return this.taskList.size();
    }

    public Task remove(int index) {
        return this.taskList.remove(index);
    }
}
