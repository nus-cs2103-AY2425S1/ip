import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

/**
 * Class that manages tasks as a collection.
 * It provides basic functionality for adding, deleting, listing, and marking tasks as done.
 */
public class TaskList {
    private Storage storage;
    private List<Task> tasks;

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
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        addTask(task, true);
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
     * @return The task string that was marked as done.
     */
    public String markTaskAsDone(int taskNumber) {
        if (taskNumber < 1 || taskNumber > this.getTaskCount()){
            throw new TaskNotExistException(String.format("BLAHH!!! The task number %s to mark as done does not exist.", taskNumber));
        }
        Task task = tasks.get(taskNumber - 1);
        task.markAsDone();
        // TODO: deprecate this, moving to Ui Class.
        // PrintUtility.wrapPrintWithHorizontalLines(
        //     "Nice! I've marked this task as done:",
        //     "  " + tasks.get(taskNumber - 1)
        // );
        //
        return task.toString();
    }

    /**
     * Marks the task with the specified task number as undone.
     *
     * @param taskNumber The task number to mark as undone.
     * @return The task string that was marked as undone.
     */
    public String markTaskAsUndone(int taskNumber) {
        if (taskNumber < 1 || taskNumber > this.getTaskCount()) {
            throw new TaskNotExistException(String.format("BLAHH!!! The task number %s to mark as done does not exist.", taskNumber));
        }
        Task task = tasks.get(taskNumber - 1);
        task.markAsUndone();
        // TODO: deprecate this, moving to Ui Class.
        // PrintUtility.wrapPrintWithHorizontalLines(
        //     "OK, I've marked this task as not done yet:",
        //     "  " + tasks.get(taskNumber - 1)
        // );
        return task.toString();
    }

    /**
     * Deletes the task with the specified task number.
     *
     * @param taskNumber The task number to delete.
     * @return The task string that was deleted.
     */
    public String deleteTask(int taskNumber) {
        if (taskNumber < 1 || taskNumber > this.getTaskCount()) {
            throw new TaskNotExistException(String.format("BLAHH!!! The task number %s to delete does not exist.", taskNumber));
        }
        Task task = tasks.remove(taskNumber - 1);
        // TODO: deprecate this, moving to Ui Class.
        // PrintUtility.wrapPrintWithHorizontalLines(
        //     "Noted. I've removed this task:",
        //     "  " + task,
        //     String.format("Now you have %s tasks in the list.", this.getTaskCount())
        // );
        return task.toString();
    }

    /**
     * TODO: deprecate this, moving to Ui Class.
     * Prints all tasks numerically in order. (Indented)
     */
    public void listTasks() {
        int taskCount = this.getTaskCount();
        for (int i = 0; i < taskCount; i++) {
            PrintUtility.indentPrint((i + 1) + ". " + tasks.get(i));
        }
    }
}
