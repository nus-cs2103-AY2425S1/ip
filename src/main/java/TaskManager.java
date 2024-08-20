import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    List<Task> tasks;
    public TaskManager() {
        tasks = new ArrayList<>();
    }
    private String getItem(int index) {
        return index + 1 + ". " + tasks.get(index);
    }
    public void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(getItem(i));
        }
    }
    public void completeTask(int index) {
        Task task = tasks.get(index);
        task.complete();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(getItem(index));
    }
    public void unCompleteTask(int index) {
        Task task = tasks.get(index);
        task.unComplete();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(getItem(index));
    }
    public void addTask(Task task) {
        System.out.println("Got it. I've added this task:");
        tasks.add(task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list");
    }
}
