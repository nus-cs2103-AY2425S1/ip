package bobby.tasklist;

import java.time.LocalDate;
import java.util.ArrayList;

import bobby.exceptions.InvalidTaskException;
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
     * @throws InvalidTaskException if the task already exists in the list
     */
    public void add(Task task) throws InvalidTaskException {
        assert task != null : "Cannot add a null task";
        if (isDuplicate(task)) {
            throw new InvalidTaskException("Task already exists: " + task.getDescription());
        }
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
     * Checks if a task is already in the list using only the description of the task.
     *
     * @param task The task to check for duplicates.
     * @return true if the task already exists, false otherwise.
     */
    public boolean isDuplicate(Task task) {
        for (Task t : tasks) {
            if (t.getDescription().equals(task.getDescription())) {
                return true;
            }
        }
        return false;
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
     * Retrieves tasks based on the provided array of string indices.
     *
     * @param indices The array of string indices for tasks to retrieve.
     * @return An {@code ArrayList} of tasks corresponding to the provided indices.
     * @throws InvalidTaskNumberException if any index is invalid or out of bounds.
     */
    public ArrayList<Task> getTasksFromIndices(String[] indices) throws InvalidTaskNumberException {
        ArrayList<Task> tasksFromIndices = new ArrayList<>();

        for (String indexStr : indices) {
            try {
                int index = Integer.parseInt(indexStr.trim()) - 1;
                Task task = get(index);
                tasksFromIndices.add(task);

            } catch (NumberFormatException e) {
                throw new InvalidTaskNumberException();
            }
        }

        return tasksFromIndices;
    }

    /**
     * Marks or unmarks multiple tasks based on the provided indices.
     *
     * @param isMarking a boolean indicating whether to mark (true) or unmark (false) the tasks
     * @param tasksToHandle an ArrayList of tasks to be marked or unmarked
     */
    public void markMultipleTasks(boolean isMarking, ArrayList<Task> tasksToHandle) {
        for (Task task : tasksToHandle) {
            if (isMarking) {
                task.markTask();
            } else {
                task.unmarkTask();
            }
        }
    }

    /**
     * Deletes multiple tasks from the task list.
     * The tasks to be deleted are provided in the form of an {@code ArrayList}.
     * If a task does not exist in the list, it will be skipped.
     *
     * @param tasksToDelete the {@code ArrayList} of tasks to delete
     */
    public void deleteMultipleTasks(ArrayList<Task> tasksToDelete) {
        ArrayList<Integer> indicesToRemove = new ArrayList<>();

        for (Task taskToDelete : tasksToDelete) {
            int index = tasks.indexOf(taskToDelete);
            if (index != -1) {
                indicesToRemove.add(index);
            }
        }

        // Sort the indices in reverse order to avoid shifting issues while removing
        indicesToRemove.sort((a, b) -> b - a);

        // Remove tasks by their indices
        for (int index : indicesToRemove) {
            tasks.remove(index);
        }
    }
}
