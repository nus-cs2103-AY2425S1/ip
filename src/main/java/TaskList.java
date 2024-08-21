import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasklist;

    public TaskList() {
        this.tasklist = new ArrayList<>();
    }

    public void addTask(String name) {
        this.tasklist.add(new Task(name));
    }

    public String toString() {
        String str = "";
        for (int i = 0; i < this.tasklist.size(); i++) {
            str += String.format("%d. ", i + 1) + this.tasklist.get(i) + "\n";
        }
        return str;
    }
}
