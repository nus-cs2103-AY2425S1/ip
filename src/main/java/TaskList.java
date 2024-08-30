import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private List<Task> tasks = new ArrayList<>();

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public int getSize() {
        return tasks.size();
    }

    public void printTasks() {
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.println(i + "." + tasks.get(i - 1).toString());
        }
    }

    public void printTask(int index) {
        System.out.println(tasks.get(index).toString());
    }

    public void setDone(int index, boolean isDone) {
        tasks.get(index).setStatus(isDone);
    }

    public void removeTask(int index) {
        tasks.remove(index);
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public void add(Task task, Storage storage) {
        tasks.add(task);
        storage.saveTasks(tasks);
    }

}
