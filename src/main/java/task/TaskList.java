package task;

import java.util.ArrayList;

/**
 * The TaskList class manages a list of Task objects.
 * It provides methods to add, delete, mark, and unmark tasks,
 * as well as to retrieve the list of tasks.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Constructs a TaskList initialized with a list of tasks.
     *
     * @param tasks An ArrayList of Task objects to initialize the TaskList.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.taskList = tasks;
    }

    /**
     * Adds a task to the TaskList.
     * Displays a confirmation message after adding the task.
     *
     * @param task The Task to be added.
     */
    public void add(Task task) {
        taskList.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    /**
     * Deletes a task from the TaskList at the specified index.
     * Displays a confirmation message after deleting the task.
     *
     * @param index The index of the task to be deleted.
     */
    public void delete(int index) {
        Task task = taskList.get(index);
        taskList.remove(index);
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    /**
     * Marks a task as done at the specified index.
     * Displays a confirmation message after marking the task.
     *
     * @param index The index of the task to be marked as done.
     */
    public void mark(int index) {
        Task task = taskList.get(index);
        task.mark();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    /**
     * Marks a task as not done at the specified index.
     * Displays a confirmation message after unmarking the task.
     *
     * @param index The index of the task to be marked as not done.
     */
    public void unmark(int index) {
        Task task = taskList.get(index);
        task.unmark();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
    }

    /**
     * Returns the list of tasks.
     *
     * @return An ArrayList of Task objects.
     */
    public ArrayList<Task> getTasks() {
        return taskList;
    }

    /**
     * Returns the string representation of the TaskList, listing all tasks.
     *
     * @return A string representation of the tasks in the list.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < taskList.size(); i++) {
            sb.append((i + 1) + ". " + taskList.get(i) + "\n");
        }
        return sb.toString();
    }
}
