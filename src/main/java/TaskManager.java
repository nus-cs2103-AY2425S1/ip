import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

/**
 * Class that manages tasks as a collection.
 * It provides basic functionality for adding, deleting, listing, and marking tasks as done.
 */
public class TaskManager {
    private static final String FILE_PATH = "../../../data/tasks.txt";
    private List<Task> tasks = new ArrayList<>();

    private class TaskIterator implements Iterator<Task> {
        private int index = 0;

        @Override
        public boolean hasNext() {
            return index < tasks.size();
        }

        @Override
        public Task next() {
            return tasks.get(index++);
        }
    }

    /**
     * Creates a task manager with tasks loaded from file.
     */
    public TaskManager() {
        TaskManagerFile.loadTasksFromFile(this, FILE_PATH);
    }

    public void addTask(Task task) {
        addTask(task, true);
    }

    public void addTask(Task task, boolean toSave) {
        tasks.add(task);
        if (toSave) {
            saveTasksToFile();
        }
    }

    public int getTaskCount() {
        return tasks.size();
    }

    public Iterator<Task> getTasksIterator() {
        return new TaskIterator();
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
        saveTasksToFile();
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
        saveTasksToFile();
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
        saveTasksToFile();
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

    /**
     * Saves the tasks to hardcoded file location.
     */
    public void saveTasksToFile() {
        TaskManagerFile.saveTasksToFile(this, FILE_PATH);
    }
}
