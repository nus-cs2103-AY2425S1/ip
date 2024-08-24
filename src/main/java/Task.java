import java.lang.reflect.Array;
import java.util.ArrayList;

public class Task {
    private String description;
    private ArrayList<Task> tasks;
    private int taskCount;
    private boolean isDone;

    public Task() {
        this.tasks = new ArrayList<Task>();
    }
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return isDone ? "[X] " : "[ ] ";
    }

    public void markAsDone() {
        isDone = true;
    }

    public void unmarkAsDone() {
        isDone = false;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int taskNumber) {
        if(taskNumber > 0 && taskNumber <= tasks.size()) {
            Task removed = tasks.remove(taskNumber - 1);
            System.out.println("Removed: " + removed);
            showNumberOfTasks();
        } else {
            System.out.println("Task number is not in range. Enter a valid task number.\n");
        }
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public int getTaskCount() {
        return taskCount;
    }

    public void showNumberOfTasks() {
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    @Override
    public String toString() {
        return getStatus() + " " + getDescription();
    }
}