package taskmanager;

import task.Task;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class TaskManager {
    protected ArrayList<Task> tasks;

    /**
     * Constructs a TaskManager.
     */
    public TaskManager() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Returns the list of tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    private boolean isDuplicate(Task task) {
        return tasks.stream()
                .anyMatch(existingTask -> existingTask.getDescription().equals(task.getDescription()));
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to add.
     * @param isSilent If true, suppress output messages.
     */
    public String addTask(Task task, boolean isSilent) {
        if (isDuplicate(task)) {
            return "This task already exists in the list.";
        }
        tasks.add(task);
        if (isSilent) {
            return null;

        }
        return "Got it. I've added this task:\n" + task.toString() + "\nNow you have " + tasks.size()
                + " tasks in the list.";


    }
    /**
     * Lists all the tasks in the task list.
     */

    public String listTasks() {
        if (tasks.isEmpty()) {
            return "No tasks in the list.";
        }

        return tasks.stream()
                .map(task -> (tasks.indexOf(task) + 1) + ". " + task)
                .collect(Collectors.joining("\n"));
    }
    /**
     * Marks the task with the given index.
     *
     * @param index Index of the task to mark.
     */
    public String markTask(int index) {
        if (isValidIndex(index)) {
            tasks.get(index).mark();
            return "Nice! I've marked this task as done:\n" + tasks.get(index).getDescription();
        }
        return "Invalid task number!";
    }
    /**
     * Unmarks the task with the given index.
     *
     * @param index Index of the task to unmark.
     */
    public String unmarkTask(int index) {
        if (isValidIndex(index)) {
            tasks.get(index).unmark();
            return "Ok. I've marked this task as not done yet:\n" + tasks.get(index).getDescription();
        }
        return "Invalid task number!";
    }
    /**
     * Deletes the task with the given index.
     *
     * @param index Index of the task to delete.
     */
    public String deleteTask(int index) {
        if (isValidIndex(index)) {
            Task removedTask = tasks.remove(index);
            return "Noted. I have removed the following task: \n" + removedTask.toString() +
                    "\nNow you have " + tasks.size() + " tasks in the list.";
        }
        return "Invalid task number!";
    }

    /**
     * Checks if index given is valid.
     *
     * @param index Index of the task to check.
     */

    private boolean isValidIndex(int index) {
        assert index >= 0 : "Index should not be negative!";
        assert index < tasks.size() : "Index should be less than the size of the tasks list!";
        return index >= 0 && index < tasks.size();
    }
}
