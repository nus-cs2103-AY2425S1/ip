import java.util.ArrayList;

public class TaskList {
    public static Task removeFromTaskList(ArrayList<Task> tasks, int index) {
        return tasks.remove(index - 1);
    }

    public static void addToTaskList(ArrayList<Task> tasks, Task t) {
        tasks.add(t);
    }

    public static void printTaskList(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("Good for you, nothing to do today :3");
            return;
        }

        for (int i = 1; i <= tasks.size(); i++) {
            System.out.printf("%d: %s%n", i, tasks.get(i - 1));
        }
    }
}
