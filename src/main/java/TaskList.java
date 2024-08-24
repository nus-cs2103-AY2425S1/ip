import java.util.ArrayList;
import luna.task.*;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public ArrayList<Task> addTask(Task task) {
        tasks.add(task);
        String taskSize = String.format("Now you have %d tasks in the list.", tasks.size());
        System.out.println("Got it. I've added this task:\n" +
                "  " + task + "\n" + taskSize);

        return tasks;
    }

    public ArrayList<Task> deleteTask(int task) throws LunaException {
        if (task >= tasks.size() || task < 0) {
            throw new LunaException("Invalid task number. Type \"list\" to view tasks.");
        }

        Task removed = tasks.remove(task);
        System.out.println("Noted, I've removed this task:\n" +
                "  " + removed.toString() + "\n" +
                "Now you have " + tasks.size() + " tasks in the list.");

        return tasks;
    }

    public void list() {
        System.out.println("Here are the tasks in your list:");

        for (int i = 0; i < tasks.size(); i++) {
            String taskStr = String.format("%d.%s", i + 1, tasks.get(i).toString());
            System.out.println(taskStr);
        }
    }

    public ArrayList<Task> markTaskAsDone(int task) throws LunaException {
        if (task >= tasks.size() || task < 0) {
            throw new LunaException("Invalid task number. Type \"list\" to view tasks.");
        }

        Task taskToMark = tasks.get(task);
        taskToMark.markAsDone();

        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + taskToMark);

        return tasks;
    }

    public ArrayList<Task> unmarkTask(int task) throws LunaException {
        if (task >= tasks.size() || task < 0) {
            throw new LunaException("Invalid task number. Type \"list\" to view tasks.");
        }

        Task taskToUnmark = tasks.get(task);
        taskToUnmark.unmark();

        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + taskToUnmark);

        return tasks;
    }
}
