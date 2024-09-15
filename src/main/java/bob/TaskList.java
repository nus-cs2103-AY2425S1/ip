package bob;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import bob.task.Task;

/**
 * Manages a list of tasks.
 * The TaskList class provides functionality to manage a collection of Task objects,
 * such as adding and deleting tasks, retrieving tasks, and printing the list of tasks.
 */
public class TaskList {
    private final List<Task> tasks;

    /**
     * Initialises an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Initialises a TaskList with a given list of tasks.
     *
     * @param tasks A list of Task objects.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Checks if the task list is empty.
     *
     * @return true if the task list is empty, false otherwise.
     */
    boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Retrieves the list of tasks.
     *
     * @return A List of Task objects.
     */

    List<Task> getTasks() {
        return tasks;
    }

    /**
     * Gets the number of tasks in the task list.
     *
     * @return Number of tasks in the task list.
     */
    int getNumTasks() {
        return tasks.size();
    }

    /**
     * Gets a Task in the task list using task number.
     *
     * @param taskNum The 1-based index of the task to retrieve.
     * @return Task object at the specified index.
     * @throws IndexOutOfBoundsException If the index is out of range.
     */
    Task getTask(int taskNum) throws BobException {
        try {
            return tasks.get(taskNum - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new BobException("The task number provided is invalid.");
        }
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added to the task list.
     */
    void addTask(Task task) {
        assert task != null : "Task to be added should not be null";
        tasks.add(task);
    }

    /**
     * Deletes a task from the task list using task number.
     *
     * @param taskNum The 1-based index of the task to be deleted.
     * @throws BobException If task number is invalid.
     */
    void delTask(int taskNum) throws BobException {
        try {
            tasks.remove(taskNum - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new BobException("The task number provided is invalid.");
        }
    }

    /**
     * Prints all tasks in the task list.
     *
     * @return A formatted string of all tasks.
     */
    String printTasks() {
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= getNumTasks(); i++) {
            Task currTask = tasks.get(i - 1);
            if (i == getNumTasks()) {
                sb.append(i).append(". ").append(currTask);
                continue;
            }
            sb.append(i).append(". ").append(currTask).append("\n");
        }
        return sb.toString();
    }

    /**
     * Prints tasks that occur on a specific date.
     *
     * @param dateStr The date to filter relevant tasks, following "yyyy-MM-dd" format
     * @return A formatted string of relevant tasks.
     * @throws BobException If the date format is invalid.
     */
    String printRelevantTasksByDate(String dateStr) throws BobException {
        try {
            // Process the date string
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(dateStr, formatter);

            // Obtain relevant tasks to be printed
            StringBuilder sb = new StringBuilder();
            int numRelevantTasks = 0;

            for (Task currTask : tasks) {
                if (currTask.isRelevant(date)) {
                    numRelevantTasks++;
                    sb.append(numRelevantTasks).append(". ").append(currTask).append("\n");
                }
            }

            DateTimeFormatter formatterWords = DateTimeFormatter.ofPattern("MMM dd yyyy");
            sb.append("Total number of relevant tasks for ")
                    .append(date.format(formatterWords)).append(": ").append(numRelevantTasks);
            return sb.toString();

        } catch (DateTimeParseException e) {
            throw new BobException("Invalid date format. Required format: relevant yyyy-MM-dd");
        }
    }

    /**
     * Prints tasks that contain a keyword or phrase within its task description.
     *
     * @param keyword The keyword or phrase to filter matching tasks.
     * @return A formatted string of matching tasks.
     * @throws BobException If the keyword is empty.
     */
    String printTasksByKeyword(String keyword) throws BobException {
        if (keyword.isEmpty()) {
            throw new BobException("Please provide a keyword or a phrase.");
        }

        StringBuilder sb = new StringBuilder();
        int numMatchingTasks = 0;

        for (Task currTask : tasks) {
            String description = currTask.getDescription();
            if (description.contains(keyword.toLowerCase())) {
                numMatchingTasks++;
                sb.append(numMatchingTasks).append(". ").append(currTask).append("\n");
            }
        }

        sb.append("Total number of tasks containing \"").append(keyword.toLowerCase())
                .append("\": ").append(numMatchingTasks);
        return sb.toString();
    }
}

