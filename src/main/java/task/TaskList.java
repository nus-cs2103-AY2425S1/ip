package task;

import exception.IndexOutOfBoundsKukiShinobuException;
import exception.KukiShinobuException;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.List;

/**
 * Represents a list of tasks.
 * <p>
 * The {@code TaskList} class manages a collection of {@link Task} objects, allowing
 * for tasks to be added, deleted, marked as done, and unmarked. It provides methods
 * to manipulate and retrieve tasks from the list.
 * </p>
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs an empty {@code TaskList}.
     * <p>
     * Initializes the task list as an empty {@code ArrayList}.
     * </p>
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Constructs a {@code TaskList} with the specified list of tasks.
     *
     * @param tasks An {@code ArrayList} of {@link Task} objects to initialize the task list.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the list of tasks.
     *
     * @return An {@code ArrayList} containing all tasks in the list.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Prints a summary message when a task is added to the list.
     * <p>
     * Displays a message confirming the addition of the task and the current number of tasks in the list.
     * </p>
     *
     * @param task The {@link Task} that has been added.
     */
    private String generateAddedTaskSummaryMessage(Task task) {
        return "Got it. I've added this task:\n" +
                task.toString() + "\n" +
                "Now you have " + this.tasks.size() + " tasks in the list.";
    }

    private String generateDeletedTaskSummaryMessage(Task task) {
        return "Noted. I've removed this task:" +
                System.lineSeparator() +
                task +
                System.lineSeparator() +
                String.format("Now you have %s tasks in the list.", this.tasks.size());
    }

    private boolean indexInRange(int i) {
        return i >= 0 && i < this.tasks.size();
    }


    /**
     * Marks a task as done based on its index in the list.
     * <p>
     * Updates the status of the task at the specified index to completed.
     * </p>
     *
     * @param taskIndex The index of the task to be marked as done (1-based index).
     */
    public String markAsDone(int taskIndex) throws KukiShinobuException {
        if (!indexInRange(taskIndex - 1)) {
            throw new IndexOutOfBoundsKukiShinobuException(taskIndex, this.tasks.size());
        }
        return this.tasks.get(taskIndex - 1).markAsDone();
    }

    /**
     * Unmarks a task as done based on its index in the list.
     * <p>
     * Updates the status of the task at the specified index to not completed.
     * </p>
     *
     * @param taskIndex The index of the task to be unmarked as done (1-based index).
     */
    public String unmarkAsDone(int taskIndex) throws KukiShinobuException {
        if (!indexInRange(taskIndex - 1)) {
            throw new IndexOutOfBoundsKukiShinobuException(taskIndex, this.tasks.size());
        }
        return this.tasks.get(taskIndex - 1).unmarkAsDone();
    }

    /**
     * Adds a task to the list.
     * <p>
     * Adds the specified {@link Task} to the list and prints a summary of the added task.
     * </p>
     *
     * @param task The {@link Task} to be added.
     */
    public String addTask(Task task) {
        this.tasks.add(task);
        return this.generateAddedTaskSummaryMessage(task);
    }

    /**
     * Deletes a task from the list based on its index.
     * <p>
     * Removes the task at the specified index from the list and prints a summary of the deleted task.
     * </p>
     *
     * @param taskIndex The index of the task to be deleted (1-based index).
     */
    public String deleteTask(int taskIndex) throws IndexOutOfBoundsKukiShinobuException {
        if (!indexInRange(taskIndex - 1)) {
            throw new IndexOutOfBoundsKukiShinobuException(taskIndex, this.tasks.size());
        }
        Task deletedTask = this.tasks.remove(taskIndex - 1);
        return this.generateDeletedTaskSummaryMessage(deletedTask);
    }


    /**
     * Returns a string representation of the task list for display purposes.
     * <p>
     * The string format is a numbered list of tasks, with each task on a new line.
     * </p>
     *
     * @return A string representation of the task list.
     */
    @Override
    public String toString() {
        if (this.tasks.isEmpty()) {
            return "There are no tasks yet! Use the `todo`, `deadline` or `event` commands to add!";
        } else {
            return "Here are the tasks in your list:"
                    + System.lineSeparator()
                    + IntStream.range(0, this.tasks.size())
                    .mapToObj(i -> (i + 1) + "." + this.tasks.get(i))
                    .collect(Collectors.joining(System.lineSeparator()));

        }
    }

    /**
     * Returns a list of tasks that match the specified keyword.
     * <p>
     * This method searches through the tasks and finds those whose descriptions
     * contain the given keyword. The search is case-sensitive.
     * </p>
     *
     * @param keyword The keyword to search for in the task descriptions.
     * @return An ArrayList of tasks that contain the keyword in their description.
     */
    public ArrayList<Task> findMatchingTasks(String keyword) {
        return this.tasks.stream()
                .filter(task -> task.description.contains(keyword))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
