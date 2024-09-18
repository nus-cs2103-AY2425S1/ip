package lebron;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Represents a list of tasks and provides methods to manipulate the tasks in
 * the list. The TaskList class supports adding, deleting, marking, unmarking,
 * and rescheduling tasks, as well as retrieving task information.
 */
public class TaskList {

    private final ArrayList<Task> tasks;

    /**
     * Constructs a TaskList with the specified list of tasks.
     *
     * @param tasks An ArrayList of tasks to initialize the task list.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added to the list.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Deletes a task from the task list at the specified index.
     *
     * @param index The 1-based index of the task to be deleted.
     * @throws LeBronException If the index is out of bounds.
     */
    public void deleteTask(int index) throws LeBronException {
        if (index <= this.tasks.size()) {
            this.tasks.remove(index - 1);
        } else {
            throw new LeBronException("Thats out of bounds bro");
        }
    }

    /**
     * Marks a task as done in the task list at the specified index.
     *
     * @param index The 1-based index of the task to be marked as done.
     * @throws LeBronException If the index is out of bounds.
     */
    public void markTask(int index) throws LeBronException {
        if (index <= this.tasks.size()) {
            this.tasks.get(index - 1).markAsDone();
        } else {
            throw new LeBronException("Thats out of bounds bro");
        }
    }

    /**
     * Marks a task as not done in the task list at the specified index.
     *
     * @param index The 1-based index of the task to be marked as not done.
     * @throws LeBronException If the index is out of bounds.
     */
    public void unmarkTask(int index) throws LeBronException {
        if (index <= this.tasks.size()) {
            this.tasks.get(index - 1).markAsUndone();
        } else {
            throw new LeBronException("Thats out of bounds bro");
        }
    }

    /**
     * Reschedules a task in the task list at the specified index to a new date.
     *
     * @param index The 1-based index of the task to be rescheduled.
     * @param newDate The new date to which the task should be rescheduled.
     * @throws LeBronException If the index is out of bounds or the task is a
     *     ToDo that cannot be rescheduled.
     */
    public void rescheduleTask(int index, LocalDate newDate) throws LeBronException {
        if (index <= this.tasks.size()) {
            Task task = this.tasks.get(index - 1);
            if (!(task instanceof ToDos)) {
                task.reschedule(newDate);
            } else {
                throw new LeBronException("Can't reschedule a ToDo bro!");
            }
        } else {
            throw new LeBronException("Thats out of bounds bro");
        }
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The size of the task list.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Returns the list of tasks.
     *
     * @return An ArrayList containing all tasks in the task list.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Retrieves a task from the task list at the specified index.
     *
     * @param index The 1-based index of the task to retrieve.
     * @return The task at the specified index.
     * @throws LeBronException If the index is out of bounds.
     */
    public Task getTask(int index) throws LeBronException {
        if (index <= this.tasks.size()) {
            return this.tasks.get(index - 1);
        } else {
            throw new LeBronException("Thats out of bounds bro");
        }
    }
}
