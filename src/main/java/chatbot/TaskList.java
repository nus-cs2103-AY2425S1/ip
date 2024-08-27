package chatbot;

import chatbot.exception.InvalidIndexException;
import chatbot.task.Task;

import java.util.ArrayList;

/**
 * Represents the task list, and contains methods that manipulate the task list
 * Contains an ArrayList of tasks
 */
public class TaskList {
    /** ArrayList of tasks encapsulated by TaskList */
    private ArrayList<Task> tasks;

    /**
     * Constructor for the TaskList object
     *
     * @param tasks ArrayList of tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Gets the ArrayList of tasks encapsulated by TaskList
     *
     * @return the ArrayList of tasks
     */
    public ArrayList<Task> getList() {
        return this.tasks;
    }

    /**
     * Gets a task from the list
     *
     * @param idx The index of the task to be returned
     * @return the Task at the index specified
     * @throws InvalidIndexException Exception thrown if the index specified is invalid
     */
    public Task get(int idx) throws InvalidIndexException {
        if (idx >= this.tasks.size() || idx < 0) {
            throw new InvalidIndexException();
        }
        return this.tasks.get(idx);
    }

    /**
     * Prints the string representation of the Task List
     */
    public void listTasks() {
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + 1 + ". " + tasks.get(i));
        }
    }

    /**
     * Modifies a task in the task list to be either completed or incomplete
     *
     * @param idx Index of the task to be modified
     * @param state Boolean value representing whether the task is complete
     * @throws InvalidIndexException Exception thrown if the index specified is invalid
     */
    public void mark(int idx, boolean state) throws InvalidIndexException {
        this.get(idx).setIsDone(state);
        if (state) {
            System.out.println("Nice! I've marked this task as done: ");
        } else {
            System.out.println("Ok, I've marked this task as not done yet: ");
        }
        System.out.println(this.get(idx));
    }

    /**
     * Removes a task from the task list
     *
     * @param idx Index of the task to be removed
     * @throws InvalidIndexException Exception thrown if the index specified is invalid
     */
    public void remove(int idx) throws InvalidIndexException {
        if (idx >= this.tasks.size() || idx < 0) {
            throw new InvalidIndexException();
        }
        Task deleted = this.tasks.remove(idx);
        System.out.println("Got it. I've removed this task: ");
        System.out.println(deleted);
        System.out.println("Now you have " + this.tasks.size() + " tasks in the list");
    }

    /**
     * Adds a task to back of the task list
     *
     * @param newTask Task to be added to the task list
     */
    public void add(Task newTask) {
        this.tasks.add(newTask);
        System.out.println("Got it. I've added this task: ");
        System.out.println(newTask);
        System.out.println("Now you have " + tasks.size() + " tasks in the list");
    }
}
