package pikappi;

import pikappi.exception.PikappiException;
import pikappi.task.DeadlineTask;
import pikappi.task.EventTask;
import pikappi.task.Task;
import pikappi.task.TodoTask;

import java.util.ArrayList;

/** Represents a list of tasks. */
public class TaskList {
    private ArrayList<Task> tasks;

    /** Returns a TaskList object that contains no tasks. */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /** Returns a TaskList object that contains tasks.
     *
     * @param tasks List of tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /** Returns a TaskList object that contains tasks.
     *
     * @return List of tasks
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /** Loads and adds a task to the list of tasks without printing statement.
     * Used for loading tasks from Storage.
     *
     * @param task Task to be added
     */
    public void load(Task task) {
        tasks.add(task);
    }

    /** Adds a task to the list of tasks.
     * Used for adding tasks from user input.
     *
     * @param task Task to be added
     */
    public void addTask(Task task) throws PikappiException {
        if (task == null) {
            throw new PikappiException("Pi-ka..?? What is the task..?");
        }
        if (task instanceof TodoTask) {
            tasks.add(task);
            System.out.println("Pi-ka-pipi! I've added this task:\n " + tasks.get(tasks.size() - 1) +
                    "\nNow you have " + tasks.size() + " tasks in the list.");
        } else if (task instanceof DeadlineTask) {
            tasks.add(task);
            System.out.println("Pi-ka-pipi! I've added this task:\n " + tasks.get(tasks.size() - 1) +
                    "\nNow you have " + tasks.size() + " tasks in the list.");
        } else if (task instanceof EventTask) {
            tasks.add(task);
            System.out.println("Pi-ka-pipi! I've added this task:\n " + tasks.get(tasks.size() - 1) +
                    "\nNow you have " + tasks.size() + " tasks in the list.");
        }
    }

    /** Deletes a task from the list of tasks.
     *
     * @param taskNum Index of the task to be deleted
     * @throws PikappiException If the task does not exist
     */
    public void deleteTask(int taskNum) throws PikappiException {
        if (taskNum > tasks.size() || taskNum < 1) {
            throw new PikappiException("Pi-ka..?? Task does not exist..");
        }
        tasks.remove(taskNum - 1);
        System.out.println("Pipi-ka-pi! I've removed this task:\n " + tasks.get(tasks.size() - 1) +
                "\nNow you have " + tasks.size() + " tasks in the list.");
    }

    /** Lists all tasks in the list of tasks. */
    public void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("Pika-ka! You have no tasks!");
            return;
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i) != null) {
                System.out.println((i + 1) + "." + tasks.get(i));
            } else {
                break;
            }
        }
    }

    public void markTask(int taskNumber) {
        tasks.get(taskNumber - 1).markAsDone();
        System.out.println("Nice! I've marked this task as done:\n" + tasks.get(taskNumber - 1));
    }

    public void unmarkTask(int taskNumber) {
        tasks.get(taskNumber - 1).unmarkAsDone();
        System.out.println("Okie, I've unmarked this task as not done yet:\n" + tasks.get(taskNumber - 1));
    }
}
