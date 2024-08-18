public class TaskManager {
    private String[] tasks;
    private int taskCount;

    public TaskManager() {
        tasks = new String[100];
        taskCount = 0;
    }

    public void listTasks() {
        System.out.println("____________________________________________________________");
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + ". " + tasks[i]);
        }
        System.out.println("____________________________________________________________");
    }

    public void addTask(String task) {
        tasks[taskCount] = task;
        taskCount++;
    }
}