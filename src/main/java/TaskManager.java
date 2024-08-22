import java.util.ArrayList;
import java.util.List;

/**
 * Class that manages tasks as a collection.
 * It provides basic functionality for adding, deleting, listing, and marking tasks as done.
 */
public class TaskManager {
    private List<Task> tasks = new ArrayList<>();

    public void addTask(Task task) {
        tasks.add(task);
    }

    public int getTaskCount() {
        return tasks.size();
    }

    /**
     * Marks the task with the specified task number as done.
     *
     * @param taskNumber The task number to mark as done.
     */
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

    /**
     * Marks the task with the specified task number as undone.
     *
     * @param taskNumber The task number to mark as undone.
     */
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

    /**
     * Deletes the task with the specified task number.
     *
     * @param taskNumber The task number to delete.
     */
    public void deleteTask(int taskNumber) {
        if (taskNumber < 1 || taskNumber > this.getTaskCount()) {
            throw new TaskNotExistException(String.format("BLAHH!!! The task number %s to delete does not exist.", taskNumber));
        }
        Task task = tasks.remove(taskNumber - 1);
        PrintUtility.wrapPrintWithHorizontalLines(
            "Noted. I've removed this task:",
            "  " + task,
            String.format("Now you have %s tasks in the list.", this.getTaskCount())
        );
    }

    /**
     * Prints all tasks numerically in order. (Indented)
     */
    public void listTasks() {
        int taskCount = this.getTaskCount();
        for (int i = 0; i < taskCount; i++) {
            PrintUtility.indentPrint((i + 1) + ". " + tasks.get(i));
        }
    }
}
