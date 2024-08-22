public class TaskManager {
    private Task[] tasks = new Task[100];
    private int taskCount = 0;

    public void addTask(Task task) {
        tasks[taskCount++] = task;
    }

    public void listTasks() {
        for (int i = 0; i < taskCount; i++) {
            PrintUtility.indentPrint((i + 1) + ". " + tasks[i]);
        }
    }
}
