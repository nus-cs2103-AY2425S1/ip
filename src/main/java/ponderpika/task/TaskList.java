package ponderpika.task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ponderpika.exception.PonderPikaException;

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

    /**
     * Adds a {@link Task} to the list.
     *
     * @param task The {@link Task} to be added to the list.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the list based on its 1-based index.
     * If the index is out of range, a PonderPikaException is thrown.
     *
     * @param index The 1-based index of the task to be deleted.
     *
     * @throws PonderPikaException If the index is invalid (i.e. out of bounds).
     */
    public void deleteTask(int index) throws PonderPikaException {
        if (index < 1 || index > tasks.size()) {
            throw new PonderPikaException("No task available at given index to be deleted!");
        }
        tasks.remove(index - 1);
    }

    /**
     * Marks a task as done based on its 1-based index.
     * If the index is out of range, a PonderPikaException is thrown.
     *
     * @param index The 1-based index of the task to be marked as done.
     *
     * @throws PonderPikaException If the index is invalid (i.e. out of bounds).
     */
    public String markTask(int index) throws PonderPikaException {
        if (index < 1 || index > tasks.size()) {
            throw new PonderPikaException("No task available at given index!");
        }
        tasks.get(index - 1).markDone();
        return "Task " + index + " has been marked as done";
    }

    /**
     * Unmarks a task based on its 1-based index.
     * If the index is out of range, a PonderPikaException is thrown.
     *
     * @param index The 1-based index of the task to be unmarked.
     *
     * @throws PonderPikaException If the index is invalid (i.e. out of bounds).
     */
    public void unmarkTask(int index) throws PonderPikaException {
        if (index < 1 || index > tasks.size()) {
            throw new PonderPikaException("No task available at given index!");
        }
        tasks.get(index - 1).markUndone();
    }

    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Prints all tasks in the list, with each task preceded by its 1-based index.
     */
    public String printTasks() {
        StringBuilder result = new StringBuilder();
        if (tasks.isEmpty()) {
            return result.append("Task list is Empty!").toString();
        }
        result.append("Here is the following tasks:\n");
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
     * @throws PonderPikaException
     */
    public String findTasks(String keyword) throws PonderPikaException {
        List<Task> matchedTasks = tasks.stream()
                .filter(t -> t.getDescription().toLowerCase().contains(keyword.toLowerCase()))
                .toList();

        if (matchedTasks.isEmpty()) {
            throw new PonderPikaException("No Matching tasks found in the list!");
        }
        return matchedTasks.stream().map(Task::toString).collect(Collectors.joining("\n"));
    }
}
