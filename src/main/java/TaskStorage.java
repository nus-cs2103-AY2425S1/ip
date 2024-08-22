import java.lang.reflect.Array;
import java.util.ArrayList;

public class TaskStorage {
    private ArrayList<Task> taskList;
    private int numberOfTasks;

    public TaskStorage() {
        this.taskList = new ArrayList<>();
        this.numberOfTasks = 0;
    }

    public void addTask(Task task) {
        this.taskList.add(task);
        System.out.println("\tAdded: " + task);
        this.numberOfTasks++;
        System.out.println("\tYou now have " + this.numberOfTasks + " tasks.");
    }

    public void deleteTask(int i) throws InvalidTaskException {
        if (i <= 0 || i > this.numberOfTasks) {
            throw new InvalidTaskException("Task does not exist!");
        }
        Task task = this.taskList.get(i - 1);
        this.taskList.remove(i - 1);
        System.out.println("\tDeleted: " + task);
        this.numberOfTasks--;
        System.out.println("\tYou now have " + this.numberOfTasks + " tasks.");
    }

    public void markTask(int i) throws InvalidTaskException {
        if (i <= 0 || i > this.numberOfTasks) {
            throw new InvalidTaskException("Task does not exist!");
        }
        Task task = this.taskList.get(i - 1);
        task.markDone();
        System.out.println("\tGood Job! The task is now marked as done: ");
        System.out.println("\tMarked task: " + task);
    }

    public void unmarkTask(int i) throws InvalidTaskException {
        if (i <= 0 || i > this.numberOfTasks) {
            throw new InvalidTaskException("Task does not exist!");
        }
        Task task = this.taskList.get(i - 1);
        task.markNotDone();
        System.out.println("\tAlright, the task is marked as not done: ");
        System.out.println("\tUnmarked task: " + task);
    }

    public void listAllTasks() {
        System.out.println("\tYou currently have " + this.numberOfTasks + " tasks.");
        for (int i = 0; i < numberOfTasks; i++) {
            int num = i + 1;
            Task task = taskList.get(i);
            System.out.println("\t" + num + ". " + task);
        }
    }
}
