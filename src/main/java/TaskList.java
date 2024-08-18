import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> taskList = new ArrayList<Task>();

    public Task getTask(int index) {
        return this.taskList.get(index);
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }

    public int getSize() {
        return this.taskList.size();
    }
}
