package quack.util;

import java.util.ArrayList;
import java.util.stream.IntStream;

import quack.exception.FailedUpdateException;
import quack.exception.InvalidCommandException;
import quack.tasks.Task;

/**
 * This class provides functionality to keep track of tasks.
 * <p>
 * It can add, remove, uupdate and delete task status.
 */
public class TaskList {

    /** Track how many tasks are in the list */
    protected int length;
    /** List to store tasks */
    protected ArrayList<Task> toDoList;

    /**
     * Creates a empty TaskList object.
     */
    public TaskList() {

        this.toDoList = new ArrayList<Task>();
        length = 0;
    }

    /**
     * Retrieves the list of tasks stored by Quack.
     * @return A list of tasks.
     */
    public ArrayList<Task> getToDoList() {

        return this.toDoList;
    }

    /**
     * Retrieves the list of tasks stored by Quack.
     * @return How many tasks are in the task list.
     */
    public int getLength() {

        return this.length;
    }

    /**
     * Updates the task staus by marking or unmarking tasks.
     * <p>
     * If the user inputs mark, then it will mark the task.
     * <p>
     * If the user inputs unmark, then it will unmark the task.
     * <p>
     * If the index input is out of bounds it will throw a invalid index exception.
     *
     * @param idx Index of the task inside the task list.
     * @param command To state weather to mark or unmark the task.
     * @throws FailedUpdateException When the task cannot be updated.
     * @throws FailedUpdateException When the command given is invalid.
     */
    public Task updateTask(int idx, String command) throws FailedUpdateException, InvalidCommandException {

        boolean signal;
        // Minus 1 because of 0 indexing
        idx = idx - 1;

        this.checkIndexInBounds(idx);

        Task task = toDoList.get(idx);

        // We need to determine which of the function to call based on the input
        if (command.equals("mark")) {
            signal = task.mark();
        } else if (command.equals("unmark")) {
            signal = task.unmark();
        } else {
            throw new InvalidCommandException(command);
        }

        // If for any reason the task did not update then we will need to update the user
        if (!signal) {
            throw new FailedUpdateException(task, command);
        }

        return task;
    }

    /**
     * Adds the task into the list.
     * @param task Task to be added into the list.
     */
    public void addTask(Task task) {

        // Ensures that the task being added to the list is not null
        assert(task != null);

        this.toDoList.add(task);
        length = this.toDoList.size();
    }

    /**
     * Deletes the task from the list.
     * <p>
     * If the index input is out of bounds it will throw a invalid index exception.
     *
     * @param idx Index of the task inside the task list.
     * @return The task that has been removed from the list.
     * @throws IndexOutOfBoundsException If the index is < 0 or if it is >= the size of the task list.
     */
    public Task deleteTask(int idx) throws IndexOutOfBoundsException {

        // Minus 1 because of 0 indexing
        idx = idx - 1;
        // Check if the index if out of bounds
        if (idx < 0 || idx >= toDoList.size()) {
            throw new IndexOutOfBoundsException("Oops looks like the index: " + (idx + 1)
                + " entered is out of bounds!");
        }

        // Remove the task from the list
        Task removedTask = this.toDoList.remove(idx);
        length = this.toDoList.size();

        return removedTask;
    }

    /**
     * Filters out tasks whos description does not contain the prompt.
     *
     * @param prompt The search citera to find tasks in the task list.
     * @return A list of tasks whos description matches the prompt.
     */
    public TaskList filterTasks(String prompt) {

        assert(prompt != null);
        TaskList filteredTasks = new TaskList();

        // Get the description of the tasks and check if it has the keyword in it
        for (Task task : this.toDoList) {
            String description = task.getDescription().toLowerCase();
            if (description.contains(prompt.toLowerCase())) {
                filteredTasks.addTask(task);
            }
        }

        return filteredTasks;
    }

    @Override
    public String toString() {

        if (this.toDoList.size() == 0) {
            return "The list is empty, why not add something!";
        } else {
            StringBuilder sb = new StringBuilder();

            IntStream.range(0, this.getLength())
                .mapToObj(i -> (i + 1) + ") " + this.toDoList.get(i).toString() + "\n")
                .forEach(sb::append);

            return sb.toString();
        }
    }

    /**
     * Checks if the index is out of bounds or not.
     * @param idx The index keyed in by the user.
     * @throws IndexOutOfBoundsException If the index is < 0 or if it is >= the size of the task list.
     */
    private void checkIndexInBounds(int idx) throws IndexOutOfBoundsException {
        if (idx < 0 || idx >= length) {
            throw new IndexOutOfBoundsException("Oops looks like the index: " + (idx + 1)
                + " entered is out of bounds!");
        }
    }
}
