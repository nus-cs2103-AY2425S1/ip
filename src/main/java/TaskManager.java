public class TaskManager {
    private Task[] tasks = new Task[100];
    private int taskCount = 0;

    public void addTask(Task task) {
        tasks[taskCount++] = task;
    }

    public void markTaskAsDone(int taskNumber) {
        tasks[taskNumber - 1].markAsDone();
        PrintUtility.wrapPrintWithHorizontalLines(
            "Nice! I've marked this task as done:",
            "  " + tasks[taskNumber - 1]
        );
    }

    public void markTaskAsUndone(int taskNumber) {
        tasks[taskNumber - 1].markAsUndone();
        PrintUtility.wrapPrintWithHorizontalLines(
            "OK, I've marked this task as not done yet:",
            "  " + tasks[taskNumber - 1]
        );
    }

    public void listTasks() {
        for (int i = 0; i < taskCount; i++) {
            PrintUtility.indentPrint((i + 1) + ". " + tasks[i]);
        }
    }
}
