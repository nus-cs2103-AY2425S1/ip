import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        try {
            this.tasks = FileHandler.loadTaskList();
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
            this.tasks = new ArrayList<>();
        }
    }

    public void addTask(Task task) {
        tasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.printf("Now you have %d task%s in the list.%n", tasks.size(), tasks.size() == 1 ? "" : "s");
        saveTaskList();
    }

    public void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("Your task list is empty.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.printf("%d.%s%n", i + 1, tasks.get(i));
            }
        }
    }

    public void markTask(int index, boolean isDone) throws AppleasterException {
        if (index < 0 || index >= tasks.size()) {
            throw new AppleasterException("Invalid task number.");
        }
        Task task = tasks.get(index);
        task.setCompleted(isDone);
        System.out.printf("Nice! I've marked this task as %s:%n", isDone ? "done" : "not done");
        System.out.println("  " + task);
        saveTaskList();
    }

    public Task deleteTask(int index) throws AppleasterException {
        if (index < 0 || index >= tasks.size()) {
            throw new AppleasterException("Invalid task number.");
        }
        Task deletedTask = tasks.remove(index);
        saveTaskList();
        return deletedTask;
    }

    public int getTaskCount() {
        return tasks.size();
    }

    private void saveTaskList() {
        try {
            FileHandler.saveTaskList(tasks);
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }
}