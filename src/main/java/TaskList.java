import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addItem(String item) {
        this.tasks.add(new Task(item));
    }

    public void addItem(Task item) {
        this.tasks.add(item);
    }

    public void markAsDone(int index) {
        this.tasks.get(index).markAsDone();
    }

    public void markAsUndone(int index) {
        this.tasks.get(index).markAsUndone();
    }

    public String getTask(int index) {
        return this.tasks.get(index).toString() + "\n";
    }

    public String getLastTask() {
        return this.tasks.getLast().toString();
    }

    public String getList() {
        StringBuilder list = new StringBuilder();
        for (int i = 0; i < this.tasks.size(); i++) {
            list.append(i + 1).append(". ").append(this.tasks.get(i)).append("\n");
        }
        return list.toString();
    }

    public int getSize() {
        return tasks.size();
    }
}
