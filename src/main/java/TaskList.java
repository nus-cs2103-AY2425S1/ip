import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
        String taskSize = String.format("Now you have %d tasks in the list.", tasks.size());
        System.out.println("Got it. I've added this task:\n" +
                "  " + task + "\n" + taskSize);
    }

    public void deleteTask(int task) {
        Task removed = tasks.remove(task);
        System.out.println("Noted, I've removed this task:\n" +
                "  " + removed.toString() + "\n" +
                "Now you have " + tasks.size() + " tasks in the list.");
    }

    public void list() {
        System.out.println("Here are the tasks in your list:");

        for (int i = 0; i < tasks.size(); i++) {
            String taskStr = String.format("%d.%s", i + 1, tasks.get(i).toString());
            System.out.println(taskStr);
        }
    }

    public void markTaskAsDone(int task) {
        Task taskToMark = tasks.get(task);
        taskToMark.markAsDone();

        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + taskToMark.toString());
    }

    public void unmarkTask(int task) {
        Task taskToUnmark = tasks.get(task);
        taskToUnmark.unmark();

        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + taskToUnmark.toString());
    }

    public int size() {
        return tasks.size();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
