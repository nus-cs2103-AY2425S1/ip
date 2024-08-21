import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasklist;

    public TaskList() {
        this.tasklist = new ArrayList<>();
    }

    public void addTask(String name) {
        this.tasklist.add(new Task(name));
    }

    public void mark(int idx) {
        if (idx < 1 || idx > this.tasklist.size()) {
            return;
        }

        this.tasklist.get(idx-1).complete();
    }

    public void unmark(int idx) {
        if (idx < 1 || idx > this.tasklist.size()) {
            return;
        }

        this.tasklist.get(idx-1).uncomplete();
    }

    public String describeTask(int idx) {
        if (idx < 1 || idx > this.tasklist.size()) {
            return "Error finding task!";
        }

        return this.tasklist.get(idx-1).toString();
    }

    public String toString() {
        String str = "";
        for (int i = 0; i < this.tasklist.size(); i++) {
            str += String.format("%d. ", i + 1) + this.tasklist.get(i) + "\n";
        }
        return str;
    }
}
