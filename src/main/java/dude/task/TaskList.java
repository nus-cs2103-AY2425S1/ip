package dude.task;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import dude.exception.DudeDuplicatedTaskException;

/**
 * Represents a list of tasks.
 * Provides methods to add, delete, mark, and unmark tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs a TaskList with an existing list of tasks.
     *
     * @param tasks An ArrayList of tasks to initialize the TaskList.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a new task to the TaskList.
     * Throws an exception if the task is a duplicate.
     *
     * @param task The task to be added.
     * @throws DudeDuplicatedTaskException If the task already exists in the TaskList.
     */
    public void addTask(Task task) throws DudeDuplicatedTaskException {
        for (Task t : tasks) {
            if (task.equals(t)) {
                throw new DudeDuplicatedTaskException();
            }
        }

        tasks.add(task);
    }

    /**
     * Deletes a task from the TaskList.
     *
     * @param index The 1-based index of the task to be deleted.
     * @return The deleted task.
     */
    public Task deleteTask(int index) {
        return tasks.remove(index - 1);
    }

    /**
     * Marks a task as done in the TaskList.
     *
     * @param index The 1-based index of the task to be marked as done.
     * @return The task that was marked as done.
     */
    public Task markTask(int index) {
        Task task = tasks.get(index - 1);
        task.markAsDone();

        return task;
    }

    /**
     * Marks a task as not done in the TaskList.
     *
     * @param index The 1-based index of the task to be unmarked.
     * @return The task that was unmarked.
     */
    public Task unmarkTask(int index) {
        Task task = tasks.get(index - 1);
        task.markAsNotDone();

        return task;
    }

    /**
     * Finds and returns a filtered list of tasks that contain the specified keyword in their description.
     *
     * @param keyword The keyword to search for in the task descriptions.
     * @return An ArrayList of tasks that contain the keyword in their description.
     *     If no tasks match the keyword, an empty list is returned.
     */
    public ArrayList<Task> findAllTask(String keyword) {
        ArrayList<Task> filteredTasks = tasks.stream()
                .filter(t -> t.description.contains(keyword))
                .collect(Collectors
                .toCollection(ArrayList::new));

        return filteredTasks;
    }

    /**
     * Gets the number of tasks in the TaskList.
     *
     * @return The number of tasks in the TaskList.
     */
    public int getLength() {
        return tasks.size();
    }

    /**
     * Gets the ArrayList of tasks.
     *
     * @return An ArrayList of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
