package reminderebot;

import reminderebot.task.Task;
import reminderebot.task.ToDo;
import reminderebot.task.Deadline;
import reminderebot.task.Event;
import java.util.ArrayList;

/**
 * TaskList represents the task manager for Reminderebot.
 */
public class TaskList {
    protected ArrayList<Task> tasks = new ArrayList<Task>();
    static int index = 0;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        index = tasks.size();
    }

    /**
     * Prints all tasks as a tasklist.
     */
    public void printTasks() {
        StringBuilder output = new StringBuilder();
        output.append("Here are the tasks in your list:\n");
        for (int i=0; i<index; i++) {
            output.append(i+1).append(".").append(tasks.get(i)).append("\n");
        }
        String taskList = output.toString();
        System.out.println(taskList);
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
        Task task = tasks.get(idx-1);
        task.markAsDone();
    }

    /**
     * Marks task as undone.
     * @param idx
     */
    public void unmarkTask(int idx) {
        Task task = tasks.get(idx-1);
        task.markAsUndone();
    }
}
