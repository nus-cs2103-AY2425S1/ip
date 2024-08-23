import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.printf("Now you have %d task%s in the list.%n", tasks.size(), tasks.size() == 1 ? "" : "s");
    }

    public void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("Your task list is empty. Why not add a task?");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.printf("%d.%s%n", i + 1, tasks.get(i));
            }
        }
    }

    public void markTask(int index, boolean isDone) throws AppleasterException {
        if (index < 0 || index >= tasks.size()) {
            throw new AppleasterException("Task " + (index + 1) + " does not exist. Please choose a number between 1 and " + tasks.size() + ".");
        }
        Task task = tasks.get(index);
        if (isDone) {
            task.markAsDone();
            System.out.println("Nice! I've marked this task as done:");
        } else {
            task.markAsNotDone();
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.println("  " + task);
    }

    public Task deleteTask(int index) throws AppleasterException {
        if (index < 0 || index >= tasks.size()) {
            throw new AppleasterException("Task " + (index + 1) + " does not exist. Please choose a number between 1 and " + tasks.size() + ".");
        }
        return tasks.remove(index);
    }

    public int getTaskCount() {
        return tasks.size();
    }
}