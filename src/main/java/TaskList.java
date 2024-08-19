import java.util.ArrayList;

public class TaskList {
    private static final ArrayList<Task> list = new ArrayList<>();

    public static void addTask(Task task) {
        list.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task.getDes());
        System.out.printf("Now you have %d tasks in the list.%n", list.size());
    }

    public static void markTask(int x) {
        Task currTask = list.get(x - 1);
        currTask.mark();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + currTask.getDes());
    }

    public static void unmarkTask(int x) {
        Task currTask = list.get(x - 1);
        currTask.unmark();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + currTask.getDes());
    }

    public static void listTask() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            Task currTask = list.get(i);
            System.out.printf("%d.%s%n", i + 1, currTask.getDes());
        }
    }

    public static void deleteTask(int x) {
        Task currTask = list.remove(x - 1);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + currTask.getDes());
        System.out.printf("Now you have %d tasks in the list.%n", list.size());
    }
}
