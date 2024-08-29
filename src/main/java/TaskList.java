import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
        System.out.println("\tOk can. I've added this task:");
        System.out.println("\t  " + task);
        System.out.println("\tNow you have " + tasks.size() + " tasks in the list.");
    }

    public void deleteTask(int index) throws InvalidTaskException {
        if (index < 0 || index >= tasks.size()) {
            throw new InvalidTaskException("Don't bother, task non-existent");
        }
        Task removedTask = tasks.remove(index);
        System.out.println("\tSigh. I've removed this task:");
        System.out.println("\t  " + removedTask);
        System.out.println("\tNow you have " + tasks.size() + " tasks in the list.");
    }

    public void markTask(int index) throws InvalidTaskException {
        if (index < 0 || index >= tasks.size()) {
            throw new InvalidTaskException("Don't bother, task non-existent");
        }
        Task task = tasks.get(index);
        task.markDone();
        System.out.println("\t0.o I've marked this as done: ");
        System.out.println("\t  " + task);
    }

    public void unmarkTask(int index) throws InvalidTaskException {
        if (index < 0 || index >= tasks.size()) {
            throw new InvalidTaskException("Don't bother, task non-existent");
        }
        Task task = tasks.get(index);
        task.markNotDone();
        System.out.println("\tAiyo, the task is marked as not done: ");
        System.out.println("\t  " + task);
    }

    public void listAllTasks() {
        System.out.println("\tSo many things to do...");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("\t" + (i + 1) + ". " + tasks.get(i));
        }
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public ArrayList<Task> getTasksOnDate(LocalDate date) {
        return tasks.stream()
                .filter(task -> task.isOnDate(date))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}