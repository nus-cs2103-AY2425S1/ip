package ponderpika.task;

import java.util.ArrayList;
import java.util.List;

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
    public void markTask(int index) throws PonderPikaException {
        if (index < 1 || index > tasks.size()) {
            throw new PonderPikaException("No task available at given index!");
        }
        tasks.get(index - 1).markDone();
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
    public void printTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).toString());
        }
    }
}
