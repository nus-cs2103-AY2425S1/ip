package bobby.tasklist;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import bobby.exceptions.InvalidInputException;
import bobby.exceptions.InvalidTaskNumberException;
import bobby.tasks.Deadline;
import bobby.tasks.Event;
import bobby.tasks.Task;
import bobby.tasks.Todo;



/**
 * The {@code TaskList} class represents a list of tasks in the Bobby application.
 * It provides methods to add, remove, and retrieve tasks, check the list's size and emptiness,
 * and find tasks by date. The list can contain various types of tasks such as
 * {@link Deadline}, {@link Event}, and {@link Todo} objects.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs an empty {@code TaskList}.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the list.
     *
     * @param task the task to be added
     */
    public void add(Task task) {
        assert task != null : "Cannot add a null task";
        tasks.add(task);
    }

    /**
     * Retrieves a task from the list by its index.
     *
     * @param index the index of the task to retrieve
     * @return the task at the specified index
     * @throws InvalidTaskNumberException if the index is out of bounds
     */
    public Task get(int index) throws InvalidTaskNumberException {
        assert index >= 0 : "Index cannot be less than 0: " + index;
        try {
            return tasks.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskNumberException();
        }

    }

    /**
     * Removes a task from the list by its index.
     *
     * @param index the index of the task to remove
     * @return the removed task
     * @throws InvalidTaskNumberException if the index is out of bounds
     */
    public Task remove(int index) throws InvalidTaskNumberException {
        assert index >= 0 : "Index cannot be less than 0: " + index;
        try {
            return tasks.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskNumberException();
        }
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return the size of the task list
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Checks if the task list is empty.
     *
     * @return {@code true} if the task list is empty, otherwise {@code false}
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Returns the list of tasks.
     *
     * @return the list of tasks
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Finds tasks that are associated with a specific date.
     * This includes tasks with deadlines on the date and events happening on the date.
     *
     * @param date The date to search for in tasks.
     * @return An ArrayList of tasks that match the date.
     */
    public ArrayList<Task> findTasksByDate(LocalDate date) {
        assert date != null : "Date must not be null";
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task instanceof Deadline && task.isOnDate(date)) {
                matchingTasks.add(task);
            } else if (task instanceof Event && task.isOnDate(date)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

    /**
     * Finds tasks containing the specified keyword in their description.
     *
     * @param keyword The keyword to search for in task descriptions.
     * @return An ArrayList of tasks that contain the keyword in their description.
     */
    public ArrayList<Task> findTasksByKeyword(String keyword) {
        assert keyword != null : "Keyword must not be null";
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

    /**
     * Marks or unmarks multiple tasks based on the provided indices.
     *
     * @param isMarking a boolean indicating whether to mark (true) or unmark (false) the tasks
     * @param args a variable number of task indices to be marked or unmarked
     * @return an ArrayList of tasks that were marked or unmarked
     * @throws InvalidInputException if the input indices are not valid integers
     * @throws InvalidTaskNumberException if a task index is out of bounds
     */
    public ArrayList<Task> markMultipleTasks(boolean isMarking, String... args)
            throws InvalidInputException, InvalidTaskNumberException {
        ArrayList<Task> processedTasks = new ArrayList<>();
        for (int i = 0; i < args.length; i++) {
            try {
                int index = Integer.parseInt(args[i]) - 1;
                Task task = tasks.get(index);
                if (isMarking) {
                    task.markTask();
                } else {
                    task.unmarkTask();
                }
                processedTasks.add(task); // Collect the processed task
            } catch (NumberFormatException e) {
                throw new InvalidInputException();
            } catch (IndexOutOfBoundsException e) {
                throw new InvalidTaskNumberException();
            }
        }
        return processedTasks;
    }

    /**
     * Deletes multiple tasks from the task list based on the provided indices.
     * The method processes each index provided in the arguments, removes the corresponding task from the list,
     * and collects the removed tasks in an {@code ArrayList}.
     * <p>
     * If an index is invalid (either non-numeric or out of range), appropriate exceptions are thrown:
     * {@code InvalidInputException} for non-numeric indices,
     * and {@code InvalidTaskNumberException} for out-of-range indices.
     * </p>
     *
     * @param args A variable number of string arguments where each string represents
     *             an index of the task to be deleted.
     * @return An {@code ArrayList} of {@code Task} objects that were removed from the task list.
     * @throws InvalidInputException if any of the arguments cannot be parsed as a valid integer.
     * @throws InvalidTaskNumberException if any of the indices are out of the valid range of the task list.
     */
    public ArrayList<Task> deleteMultipleTasks(String... args)
            throws InvalidInputException, InvalidTaskNumberException {
        ArrayList<Task> processedTasks = new ArrayList<>();
        List<Integer> indices = new ArrayList<>();
        // Parse and collect indices
        for (String arg : args) {
            try {
                int index = Integer.parseInt(arg) - 1; // Convert to 0-based index
                if (index < 0) {
                    throw new InvalidInputException(); // Index must be non-negative
                }
                indices.add(index);
            } catch (NumberFormatException e) {
                throw new InvalidInputException();
            }
        }

        // Sort indices in descending order to prevent shifting issues
        Collections.sort(indices, Collections.reverseOrder());


        for (int index : indices) {
            try {
                Task removedTask = this.remove(index);
                processedTasks.add(removedTask); // Collect the processed task
            } catch (IndexOutOfBoundsException e) {
                throw new InvalidTaskNumberException();
            }
        }
        return processedTasks;
    }
}
