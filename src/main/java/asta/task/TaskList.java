package asta.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import asta.AstaException;

/**
 * Represents a list of tasks. Provides methods to add, remove, mark, unmark tasks, and list all tasks.
 */
public class TaskList {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private final ArrayList<Task> tasks;

    /**
     * Creates an empty TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Creates a TaskList with the given list of tasks.
     *
     * @param tasks The list of tasks to initialize with.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Retrieves a task by its index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     * @throws AstaException If the index is out of bounds.
     */
    public Task getTask(int index) throws AstaException {
        if (index < 0 || index >= tasks.size()) {
            throw new AstaException("Invalid task number.");
        }
        return tasks.get(index);
    }

    /**
     * Returns a copy of the list of tasks.
     *
     * @return A list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return new ArrayList<>(tasks);
    }

    /**
     * Gets the size of the task list.
     *
     * @return The number of tasks in the list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Lists all tasks as a string.
     */
    public String listTasks() {
        StringBuilder response = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            response.append((i + 1)).append(". ").append(tasks.get(i)).append("\n");
        }
        return response.toString();
    }

    /**
     * Marks a task as done or not done.
     *
     * @param taskNumber The index of the task to mark.
     * @param markAsDone True to mark the task as done, false to mark as not done.
     * @throws AstaException If the task number is invalid.
     */
    public void markTask(int taskNumber, boolean markAsDone) throws AstaException {
        if (taskNumber >= 0 && taskNumber < tasks.size()) {
            if (markAsDone) {
                tasks.get(taskNumber).markAsDone();
            } else {
                tasks.get(taskNumber).markAsNotDone();
            }
        } else {
            if (markAsDone) {
                AstaException.handleInvalidMarkTaskNumber();
            } else {
                AstaException.handleInvalidUnmarkTaskNumber();
            }
        }
    }

    /**
     * Adds a new ToDo task to the list.
     *
     * @param description The description of the ToDo task.
     * @throws AstaException If the description is empty.
     */
    public void addTodoTask(String description) throws AstaException {
        if (description.isEmpty()) {
            AstaException.handleEmptyTodoDescription();
        }
        tasks.add(new ToDo(description));
    }

    /**
     * Adds a new Deadline task to the list.
     *
     * @param description The description of the Deadline task.
     * @param byDateStr   The date by which the task should be completed.
     * @throws AstaException If the description is empty or the date format is invalid.
     */
    public void addDeadlineTask(String description, String byDateStr) throws AstaException {
        if (description.isEmpty()) {
            AstaException.handleInvalidDeadlineInput();
        }
        try {
            LocalDateTime by = LocalDateTime.parse(byDateStr.trim(), DATE_TIME_FORMATTER);
            tasks.add(new Deadline(description, by));
        } catch (DateTimeParseException e) {
            throw new AstaException("Invalid date format. Please use dd/MM/yyyy HHmm format.");
        }
    }


    /**
     * Adds a new Event task to the list.
     *
     * @param description The description of the Event task.
     * @param fromDateStr The start date of the event.
     * @param toDateStr   The end date of the event.
     * @throws AstaException If the description is empty or the date format is invalid.
     */
    public void addEventTask(String description, String fromDateStr, String toDateStr) throws AstaException {
        if (description.isEmpty()) {
            AstaException.handleInvalidEventInput();
        }
        try {
            LocalDateTime from = LocalDateTime.parse(fromDateStr.trim(), DATE_TIME_FORMATTER);
            LocalDateTime to = LocalDateTime.parse(toDateStr.trim(), DATE_TIME_FORMATTER);
            tasks.add(new Event(description, from, to));
        } catch (DateTimeParseException e) {
            throw new AstaException("Invalid date format. Please use dd/MM/yyyy HHmm format.");
        }
    }

    /**
     * Deletes a task from the list.
     *
     * @param index The index of the task to delete.
     * @return The deleted task.
     * @throws AstaException If the task number is invalid.
     */
    public Task deleteTask(int index) throws AstaException {
        if (index < 0 || index >= tasks.size()) {
            AstaException.handleInvalidDeleteTaskNumber();
        }
        return tasks.remove(index);
    }

    /**
     * Retrieves the task number from a given input string.
     *
     * @param input  The input string containing the task number.
     * @param action The action being performed (e.g., "mark", "delete").
     * @return The task number.
     * @throws AstaException If the task number format is invalid.
     */
    public int getTaskNumber(String input, String action) throws AstaException {
        try {
            return Integer.parseInt(input.substring(action.length() + 1).trim()) - 1;
        } catch (NumberFormatException e) {
            AstaException.handleInvalidTaskNumberFormat(action);
            return -1;
        }
    }

    /**
     * Finds tasks that contain the specified keyword.
     *
     * @param keyword The keyword to search for in task descriptions.
     */
    public List<Task> findTasks(String keyword) {
        return tasks.stream()
                .filter(task -> task.getDescription().contains(keyword))
                .collect(Collectors.toList());
    }
}
