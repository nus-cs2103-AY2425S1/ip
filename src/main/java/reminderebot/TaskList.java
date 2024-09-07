package reminderebot;

import java.util.ArrayList;

import reminderebot.task.Task;
import reminderebot.task.ToDo;
import reminderebot.task.Deadline;
import reminderebot.task.Event;



/**
 * TaskList represents the task manager for Reminderebot.
 */
public class TaskList {
    private int index = 0;
    private ArrayList<Task> tasks = new ArrayList<Task>();

    /**
     * Instantiate a TaskList
     * @param tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        index = tasks.size();
    }

    /**
     * Returns all tasks as a string in a tasklist.
     * @return tasklist
     */
    public String printTasks() {
        StringBuilder output = new StringBuilder();
        output.append("Here are the tasks in your list:\n");
        for (int i = 0; i < index; i++) {
            output.append(i + 1).append(".").append(tasks.get(i)).append("\n");
        }
        String taskList = output.toString();
        return taskList;
    }

    /**
     * Get Task at index.
     * @param idx
     * @return task
     */
    public Task getTask(int idx) {
        return tasks.get(idx);
    }

    /**
     * Get number of tasks.
     * @return length
     */
    public int length() {
        return tasks.size();
    }

    /**
     * Add a to-do, deadline or event to the tasklist.
     * @param task
     */
    public void addTask(Task task) {
        tasks.add(task);
        index++;
    }

    /**
     * Remove a to-do, deadline or event from the tasklist.
     * @param idx
     * @return removed Task
     */
    public Task deleteTask(int idx) {
        index--;
        return tasks.remove(idx - 1);
    }

    /**
     * Marks task as done.
     * @param idx
     */
    public void markTask(int idx) {
        Task task = tasks.get(idx - 1);
        task.markAsDone();
    }

    /**
     * Marks task as undone.
     * @param idx
     */
    public void unmarkTask(int idx) {
        Task task = tasks.get(idx - 1);
        task.markAsUndone();
    }

    /**
     * Debug method for printing all tasks in the tasklist
     * @param msg
     */
    private void debugPrint(Object... msg) {
        for (Object item : msg) {
            System.out.print(item);
        }
        System.out.println();
    }
}
