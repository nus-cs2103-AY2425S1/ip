import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public boolean addTask(Task task) {
        return tasks.add(task);
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public boolean markTask(int index) {
        return tasks.get(index).completeTask();
    }

    public boolean unmarkTask(int index) {
        return tasks.get(index).uncompleteTask();
    }

    @Override
    public String toString() {
        String res = "";
        for (int i = 0; i < tasks.size(); i++) {
            res = res + (i + 1) + ". " + tasks.get(i) + "\n";
        }
        return res;
    }
}
