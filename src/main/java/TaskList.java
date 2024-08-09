public class TaskList {
    private static String[] tasks;
    private static int taskCount;

    public TaskList() {
        tasks = new String[100];
        taskCount = 0;
    }

    public void addTask(String task) {
        if (taskCount <= 100) {
            tasks[taskCount++] = task;
            System.out.println("added: " + task);
        } else {
            System.out.println("Task list is full!");
        }
    }

    public void listTasks() {
        if (taskCount == 0) {
            System.out.println("No tasks.");
            return;
        }

        for (int i = 0; i < taskCount; i ++) {
            System.out.println((i + 1) + ". " + tasks[i]);
        }
    }
}
