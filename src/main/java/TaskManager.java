public class TaskManager {
    private int taskCount;
    private String[] tasks;
    public TaskManager() {
        taskCount = 0;
        tasks = new String[100];
    }
    public void addTask(String task) {
        tasks[taskCount] = task;
        taskCount++;
        System.out.println("____________________________________________________________");
        System.out.println("added: " + task);
        System.out.println("____________________________________________________________");
    }

    public void listTask() {
        System.out.println("____________________________________________________________");
        for (int i = 0; i < taskCount; i++) {
            System.out.println(" " + (i + 1) + ". " + tasks[i]);
        }
        System.out.println("____________________________________________________________");
    }
}
