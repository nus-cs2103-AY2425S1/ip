import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents the list of tasks handled by the application.
 */
public class Tasks {
    /**
     * The list of tasks.
     */
    private ArrayList<Task> tasks;

    /**
     * The saved tasks.
     */
    private SavedTasks savedTasks;

    /**
     * Constructor for a new list of tasks.
     *
     * @param path The path of the file to save the list of tasks.
     */
    public Tasks(String path) throws IOException {
        this.savedTasks = new SavedTasks(path);
        this.tasks = this.savedTasks.load();
    }

    /**
     * Creates and adds a to-do task to the list, and returns it.
     *
     * @param name The name of the to-do task.
     * @return The created to-do task.
     */
    public Task todo(String name) throws IOException {
        ToDo task = new ToDo(name);

        this.tasks.add(task);
        this.savedTasks.add(task);

        return task;
    }

    /**
     * Creates and adds a deadline task to the list, and returns it.
     *
     * @param name The name of the deadline task.
     * @param deadline The deadline of the task.
     * @return The created deadline task.
     */
    public Task deadline(String name, String deadline) throws IOException {
        Deadline task = new Deadline(name, deadline);

        this.tasks.add(task);
        this.savedTasks.add(task);

        return task;
    }

    /**
     * Creates and adds an event task to the list, and returns it.
     *
     * @param name The name of the event task.
     * @param start The start of the event.
     * @param end The end of the event.
     * @return The created event task.
     */
    public Task event(String name, String start, String end) throws IOException {
        Event task = new Event(name, start, end);

        this.tasks.add(task);
        this.savedTasks.add(task);

        return task;
    }

    /**
     * Returns a task by item number.
     *
     * @param itemNum The item number of the task to return.
     * @return The task with the specified item number.
     * @throws InvalidInputException If task with the specified item number does not exist.
     */
    public Task get(int itemNum) throws InvalidInputException {
        if (itemNum <= 0 || itemNum > this.tasks.size()) {
            throw new InvalidInputException("I'm sorry, I could not find task number " + itemNum + ".");
        }

        return this.tasks.get(itemNum - 1);
    }

    /**
     * Deletes a task by item number, and returns it.
     *
     * @param itemNum The item number of the task to delete.
     * @return The deleted task with the specified item number.
     * @throws InvalidInputException If task with the specified item number does not exist.
     */
    public Task delete(int itemNum) throws InvalidInputException, IOException {
        Task task = this.get(itemNum);

        this.tasks.remove(itemNum - 1);
        this.savedTasks.delete(itemNum);

        return task;
    }

    /**
     * Marks a task as completed or not completed by item number, and returns it.
     *
     * @param itemNum The item number of the task to mark.
     * @param isCompleted Whether to mark the task as completed or not completed.
     * @throws InvalidInputException If the task with the specified item number does not exist.
     */
    public Task mark(int itemNum, boolean isCompleted) throws InvalidInputException, IOException {
        Task task = this.get(itemNum);

        task.mark(isCompleted);
        this.savedTasks.update(itemNum, task);

        return task;
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The number of tasks in the list.
     */
    public int size() {
        return this.tasks.size();
    }
}
