import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private List<Task> tasks = new ArrayList<>();

    public void addTask(Task task) {
        tasks.add(task);
    }

    public int getTaskCount() {
        return tasks.size();
    }

    public void markTaskAsDone(int taskNumber) {
        if (taskNumber < 1 || taskNumber > this.getTaskCount()){
            throw new TaskNotExistException(String.format("BLAHH!!! The task number %s to mark as done does not exist.", taskNumber));
        }
        tasks.get(taskNumber - 1).markAsDone();
        PrintUtility.wrapPrintWithHorizontalLines(
            "Nice! I've marked this task as done:",
            "  " + tasks.get(taskNumber - 1)
        );
    }

    public void markTaskAsUndone(int taskNumber) {
        if (taskNumber < 1 || taskNumber > this.getTaskCount()) {
            throw new TaskNotExistException(String.format("BLAHH!!! The task number %s to mark as done does not exist.", taskNumber));
        }
        tasks.get(taskNumber - 1).markAsUndone();
        PrintUtility.wrapPrintWithHorizontalLines(
            "OK, I've marked this task as not done yet:",
            "  " + tasks.get(taskNumber - 1)
        );
    }

    public void listTasks() {
        int taskCount = this.getTaskCount();
        for (int i = 0; i < taskCount; i++) {
            PrintUtility.indentPrint((i + 1) + ". " + tasks.get(i));
        }
    }
}
