package wiggly.task;

import java.util.ArrayList;
import java.util.List;

/**
 * A class representation of an ordered, mutable collection of {@code Task}
 */
public class TaskList {

    private final List<Task> tasks;

    /**
     * Creates a {@code TaskList} instance from a {@code List<Task>}
     *
     * @param tasks the specified {@code List<Task>}
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Creates a {@code TaskList} instance containing no tasks
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Adds a {@code Task} into the {@code TaskList}
     *
     * @param task the {@code Task} to be added
     * @return a message to indicate the added {@code Task}
     */
    public String addTask(Task task) {
        tasks.add(task);
        return "Got it. I've added this task:\n" + task + "\n"
            + "Now you have " + tasks.size() + " tasks in the list!";
    }

    /**
     * Returns the {@code Task} at the specified index of {@code TaskList}
     *
     * @param index The index of {@code Task} to return
     * @return The {@code Task} at the specified index
     */
    private Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Marks the {@code Task} of the given taskNumber as done. If the task is already done, it will remain the same
     *
     * @param taskNumber the task number of the {@code Task}
     * @return a message to indicate that the mark was successful
     */
    public String markDone(int taskNumber) {
        assert taskNumber <= tasks.size() && taskNumber > 0 : "taskNumber out of bounds";

        StringBuilder sb = new StringBuilder();
        Task task = getTask(taskNumber - 1);
        task.markDone();
        sb.append("Nice! I've marked this task as done:\n");
        sb.append(task);
        return sb.toString();
    }

    /**
     * Marks the {@code Task} of the given taskNumber as undone.
     * If the task is already marked as undone, it will remain the same
     *
     * @param taskNumber the task number of the {@code Task}
     * @return a message to indicate that the mark was successful
     */
    public String markUndone(int taskNumber) {
        assert taskNumber <= tasks.size() && taskNumber > 0 : "taskNumber out of bounds";

        StringBuilder sb = new StringBuilder();
        Task task = getTask(taskNumber - 1);
        task.markUndone();
        sb.append("Ok, I've marked this task as not done yet:\n");
        sb.append(task);
        return sb.toString();
    }

    /**
     * Deletes the {@code Task} of the given taskNumber.
     *
     * @param taskNumber the task number of the {@code Task}
     * @return a message to indicate that the task has been deleted
     */
    public String deleteTask(int taskNumber) {
        assert taskNumber <= tasks.size() && taskNumber > 0 : "taskNumber out of bounds";

        StringBuilder sb = new StringBuilder();
        Task task = getTask(taskNumber - 1);
        this.tasks.remove(task);
        sb.append("Noted. I've removed this task:\n");
        sb.append(task).append("\n");
        sb.append("Now you have ").append(tasks.size()).append(" tasks in the list!");
        return sb.toString();
    }

    /**
     * Returns a String representation of the entire task list to be stored in a data file
     *
     * @return The converted string
     */
    public String toFileFormat() {
        StringBuilder sb = new StringBuilder();
        for (Task task : tasks) {
            sb.append(task.toFileFormat()).append("\n");
        }
        return sb.toString();
    }

    /**
     * Returns a String representation of the task list
     *
     * @return a String representation of the task list
     */
    @Override
    public String toString() {
        int count = 1;
        StringBuilder sb = new StringBuilder();

        assert tasks != null : "Task List should not be null";
        for (Task task : tasks) {
            assert task != null : "Task must not be null";
            sb.append(count).append(". ").append(task.toString()).append("\n");
            count++;
        }

        return sb.toString();
    }

    /**
     * Returns a String representation of a filtered task list with tasks that passed {@code task.contains(keyword)}
     *
     * @param keyword The string to check
     * @return The String representation of the filtered task list
     */
    public String search(String keyword) {
        StringBuilder sb = new StringBuilder();
        int count = 1;
        for (Task t : tasks) {
            if (t.contains(keyword)) {
                sb.append(count).append(". ").append(t).append("\n");
            }
            count++;
        }
        return sb.toString();
    }

}
