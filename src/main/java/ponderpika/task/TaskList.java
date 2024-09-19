package ponderpika.task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ponderpika.exception.DuplicateTaskException;
import ponderpika.exception.InvalidPriorityException;
import ponderpika.exception.InvalidTaskIndexException;
import ponderpika.exception.NoMatchingTasksFoundException;
import ponderpika.exception.TaskAlreadyMarkedException;
import ponderpika.exception.TaskAlreadyUnmarkedException;

/**
 * Represents a list of Task objects and provides methods to manage them.
 * <p>
 * The TaskList class allows adding, deleting, marking, and unmarking tasks, as well as
 * retrieving and printing the list of tasks.
 * </p>
 */
public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public List<Task> getTasks() {
        return tasks;
    }

    private boolean checkDuplicateTask(Task t) {
        for (Task task : this.tasks) {
            if (task.getDescription().trim().equals(t.getDescription().trim())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds a {@link Task} to the list.
     *
     * @param task The {@link Task} to be added to the list
     * @throws DuplicateTaskException if the task already exists in list
     */
    public void addTask(Task task) throws DuplicateTaskException {
        System.out.println(checkDuplicateTask(task));
        if (checkDuplicateTask(task)) {
            throw new DuplicateTaskException();
        }
        tasks.add(task);
    }

    /**
     * Deletes a task from the list based on its 1-based index.
     * If the index is out of range, a PonderPikaException is thrown.
     *
     * @param index The 1-based index of the task to be deleted.
     *
     * @throws InvalidTaskIndexException If the index is invalid (i.e. out of bounds).
     */
    public void deleteTask(int index) throws InvalidTaskIndexException {
        if (index < 1 || index > tasks.size()) {
            throw new InvalidTaskIndexException();
        }
        tasks.remove(index - 1);
    }

    /**
     * Marks a task as done based on its 1-based index.
     * If the index is out of range, a PonderPikaException is thrown.
     *
     * @param index The 1-based index of the task to be marked as done.
     *
     * @throws InvalidTaskIndexException If the index is invalid (i.e. out of bounds)
     * @throws TaskAlreadyMarkedException If the task is already done (already marked)
     */
    public String markTask(int index) throws InvalidTaskIndexException, TaskAlreadyMarkedException {
        if (index < 1 || index > tasks.size()) {
            throw new InvalidTaskIndexException();
        }
        boolean alreadyMarked = tasks.get(index - 1).isDone();
        if (alreadyMarked) {
            throw new TaskAlreadyMarkedException();
        }

        assert index > 0;
        tasks.get(index - 1).markDone();
        return "Task " + index + " has been marked as done";
    }

    /**
     * Unmarks a task based on its 1-based index.
     * If the index is out of range, a PonderPikaException is thrown.
     *
     * @param index The 1-based index of the task to be unmarked.
     *
     * @throws InvalidTaskIndexException If the index is invalid (i.e. out of bounds)
     * @throws TaskAlreadyUnmarkedException if task is not done yet (still unmarked)
     */
    public String unmarkTask(int index) throws InvalidTaskIndexException, TaskAlreadyUnmarkedException {
        if (index < 1 || index > tasks.size()) {
            throw new InvalidTaskIndexException();
        }
        boolean alreadyUnmarked = tasks.get(index - 1).isDone();
        if (!alreadyUnmarked) {
            throw new TaskAlreadyUnmarkedException();
        }

        tasks.get(index - 1).markUndone();
        return "Task " + index + " has been unmarked!";
    }

    /**
     * Sets the priority level of a specified task in the task list.
     * <p>
     * This method updates the priority of the task at the given index to the specified priority level.
     * Valid priority levels are "H" for high, "M" for medium, "L" for low, and "N" for none
     * </p>
     *
     * @param index the index of the task to update in the task list
     * @param priority the new priority level to set for the task
     * @return a confirmation message indicating the new priority level and the task description
     * @throws InvalidTaskIndexException if the provided index is out of bounds
     * @throws InvalidPriorityException if the provided priority level is not valid
     */
    public String setPriorityLevel(int index, String priority) throws InvalidTaskIndexException,
            InvalidPriorityException {
        if (index < 0 || index > tasks.size()) {
            throw new InvalidTaskIndexException();
        }
        boolean possiblePriority = (priority.equalsIgnoreCase("H")
                || priority.equalsIgnoreCase("M") || priority.equalsIgnoreCase("L")
                || priority.equalsIgnoreCase("N"));
        if (!possiblePriority) {
            throw new InvalidPriorityException();
        }
        tasks.get(index).setPriority(priority);
        return String.format("Priority set to %s for %s", priority.toUpperCase(), tasks.get(index).getDescription());
    }

    /**
     * Prints all tasks in the list, with each task preceded by its 1-based index.
     */
    public String printTasks() {
        StringBuilder result = new StringBuilder();
        if (tasks.isEmpty()) {
            return result.append("Task list is Empty!").toString();
        }
        result.append("Here are the following tasks:\n");
        for (int i = 0; i < tasks.size(); i++) {
            result.append((i + 1)).append(". ").append(tasks.get(i).toString()).append("\n");
        }
        return result.toString();
    }

    /**
     * finds the tasks with matched keyword given by user*
     *
     * @param keyword keyword to be searched in the descriptions of tasks
     * @return tasks with keyword in it
     * @throws NoMatchingTasksFoundException for tasks not found in the list using keyword
     */
    public String findTasks(String keyword) throws NoMatchingTasksFoundException {
        List<Task> matchedTasks = tasks.stream()
                .filter(t -> t.getDescription().toLowerCase().contains(keyword.toLowerCase()))
                .toList();

        if (matchedTasks.isEmpty()) {
            throw new NoMatchingTasksFoundException();
        }
        return matchedTasks.stream().map(Task::toString).collect(Collectors.joining("\n"));
    }
}
