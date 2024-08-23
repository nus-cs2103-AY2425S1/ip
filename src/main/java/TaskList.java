import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public int getIndex(Task task) {
        return tasks.indexOf(task);
    }

    public void replaceTask(int index, Task newTask) {
        tasks.set(index, newTask);
    }

    public void deleteTask(int index) {
        tasks.remove(index);
    }

    public int getCount() {
        return tasks.size();
    }

}
