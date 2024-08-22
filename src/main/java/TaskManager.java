public class TaskManager {
    private static Task[] tasks = new Task[100];
    private static int taskCount = 0;

    public static addTask(Task task) {
        tasks[taskCount++] = task;
    }

    public static void listTasks() {
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + ". " + tasks[i]);
        }
    }
}
