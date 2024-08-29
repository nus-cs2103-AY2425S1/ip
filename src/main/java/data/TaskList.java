package data;

import exceptions.InvalidTaskException;
import ui.Ui;

import java.util.ArrayList;

/**
 * Represents the list of tasks
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Constructor for TaskList
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Loads a task into the list. Does not show user any information about loading
     *
     * @param task a Task to be loaded in
     */
    public void loadTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Adds a task into the list. Shows user success message upon completion
     *
     * @param task a new Task to be added in
     * @param ui user interface to give user information
     */
    public void addTask(Task task, Ui ui) {
        this.taskList.add(task);
        ui.displayString("Added: " + task);
        ui.displayString("You now have " + taskList.size() + " tasks.");
    }

    /**
     * Deletes a task from the list. Shows user success message upon completion
     *
     * @param i task number of task to be deleted from
     * @param ui user interface to give user information
     * @throws InvalidTaskException if i < 0  or i > taskList.getSize()
     */
    public void deleteTask(int i, Ui ui) throws InvalidTaskException {
        int numberOfTasks = this.taskList.size();
        if (i <= 0 || i > numberOfTasks) {
            throw new InvalidTaskException("Task does not exist!");
        }
        Task task = this.taskList.get(i - 1);
        this.taskList.remove(i - 1);
        numberOfTasks--;

        ui.displayString("Deleted: " + task);
        ui.displayString("You now have " + numberOfTasks + " tasks.");
    }

    /**
     * Marks task as complete. Shows user success message upon completion.
     *
     * @param i task number of the task to be marked
     * @param ui user interface to give user information
     * @throws InvalidTaskException if i < 0  or i > taskList.getSize()
     */
    public void markTask(int i, Ui ui) throws InvalidTaskException {
        int numberOfTasks = this.taskList.size();
        if (i <= 0 || i > numberOfTasks) {
            throw new InvalidTaskException("Task does not exist!");
        }
        Task task = this.taskList.get(i - 1);
        task.markDone();

        ui.displayString("Good Job! The task is now marked as done: ");
        ui.displayString("Marked task: " + task);
    }

    /**
     * Marks task as not complete. Shows user success message upon completion.
     *
     * @param i task number of task to be unmarked
     * @param ui user interface to give user information
     * @throws InvalidTaskException if i < 0  or i > taskList.getSize()
     */
    public void unmarkTask(int i, Ui ui) throws InvalidTaskException {
        int numberOfTasks = this.taskList.size();
        if (i <= 0 || i > numberOfTasks) {
            throw new InvalidTaskException("Task does not exist!");
        }
        Task task = this.taskList.get(i - 1);
        task.markNotDone();

        ui.displayString("Alright, the task is marked as not done: ");
        ui.displayString("Unmarked task: " + task);
    }

    /**
     * Lists all tasks and shows them to user.
     *
     * @param ui user interface to show user information
     */
    public void listAllTasks(Ui ui) {
        int numberOfTasks = taskList.size();
        ui.displayString("You currently have " + numberOfTasks + " tasks.");
        for (int i = 0; i < numberOfTasks; i++) {
            int num = i + 1;
            Task task = taskList.get(i);
            ui.displayString(num + ". " + task);
        }
    }

    /**
     * Method to get number of tasks
     *
     * @return number of tasks in list
     */
    public int getSize() {
        return taskList.size();
    }

    /**
     * Method to get a specific tasks
     *
     * @param i task to get based on index
     * @return Task at given index
     */
    public Task getTask(int i) {
        return taskList.get(i);
    }

}
