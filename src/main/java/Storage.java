import java.util.ArrayList;

public class Storage {
    private ArrayList<Task> taskList;

    public Storage() {
        this.taskList = new ArrayList<Task>();
    }

    public void addTask(Task t) {
        this.taskList.add(t);
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }
}