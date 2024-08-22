import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public void addItem(String item) {
        tasks.add(new Task(item));
    }

    public void addItem(Task item) {
        tasks.add(item);
    }

    public void markAsDone(int index) {
        tasks.get(index).markAsDone();
    }

    public void markAsUndone(int index) {
        tasks.get(index).markAsUndone();
    }

    public String getTask(int index) {
        return tasks.get(index).toString() + "\n";
    }

    public String getLastTask() {
        return tasks.getLast().toString();
    }

    public String getList() {
        StringBuilder list = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            list.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        return list.toString();
    }

    public int getSize() {
        return tasks.size();
    }
}
