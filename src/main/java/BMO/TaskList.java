package bmo;

import java.io.IOException;
import java.util.ArrayList;

import bmo.task.Deadline;
import bmo.task.Event;
import bmo.task.Task;
import bmo.task.ToDo;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>(100);
    }

    /**
     * Adds a task to the task list.
     *
     * @param task the task to be added.
     */
    public void addTask(Task task) {
        assert task != null : "Null task cannot be added";
        this.tasks.add(task);
    }

    /**
     * Returns the list of tasks.
     *
     * @return ArrayList of all tasks.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Returns the task at the specified index.
     *
     * @param index of the task to be retrieved.
     * @return Task object at the specified index.
     */
    public Task getTask(int index) {
        assert index >= 0 : "Index cannot be negative";
        return this.tasks.get(index);
    }

    /**
     * Returns the number of tasks in the list.
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Deletes a task from the list
     *
     * @param index of the task to be deleted.
     */
    public void deleteTask(int index) {
        assert index >= 0 : "Index cannot be negative";
        this.tasks.remove(index);
    }

    /**
     * Marks a task as completed
     *
     * @param index of the task to be marked as completed.
     */
    public void markTask(int index) {
        assert index >= 0 : "Index cannot be negative";
        this.getTask(index).mark();
    }

    /**
     * Unmarks a task as incomplete.
     *
     * @param index of the task to be unmarked.
     */
    public void unmarkTask(int index) {
        assert index >= 0 : "Index cannot be negative";
        this.getTask(index).unmark();
    }

    /**
     * Adds a todo task to the list.
     *
     * @param description of the todo task.
     * @return boolean indicating if the task was added successfully.
     */
    public boolean addTodo(String description) {
        assert description != "" : "Description cannot be empty";
        Task todo = new ToDo(description);
        if (hasNoDuplicates(todo)) {
            this.addTask(todo);
            return true;
        }
        return false;
    }

    /**
     * Adds a deadline task to the list.
     *
     * @param description of the deadline task.
     * @param by the deadline of the task. Format: "dd/MM/yyyy"
     * @return boolean indicating if the task was added successfully.
     */
    public boolean addDeadline(String description, String by) {
        assert description != "" : "Description cannot be empty";
        assert by != "" : "Deadline cannot be empty";
        Task deadline = new Deadline(description, by);
        if (hasNoDuplicates(deadline)) {
            this.addTask(deadline);
            return true;
        }
        return false;
    }

    /**
     * Adds an event task to the list.
     *
     * @param description of the event task.
     * @param from the start date of the event. Format: "dd/MM/yyyy"
     * @param to the end date of the event. Format: "dd/MM/yyyy"
     * @return boolean indicating if the task was added successfully.
     */
    public boolean addEvent(String description, String from, String to) throws IOException {
        assert description != "" : "Description cannot be empty";
        assert from != "" : "Start date cannot be empty";
        assert to != "" : "End date cannot be empty";
        Task event = new Event(description, from, to);
        if (hasNoDuplicates(event)) {
            this.addTask(event);
            return true;
        }
        return false;
    }

    /**
     * Finds tasks with a description that matches the keyword.
     *
     * @param keyword String to be searched for in the task descriptions.
     * @return TaskList containing tasks with matching descriptions.
     */
    public TaskList findMatchingTasks(String keyword) {
        assert keyword != "" : "Keyword cannot be empty";
        TaskList matchingTasks = new TaskList();
        for (Task task : this.tasks) {
            if (task.hasMatchingDescription(keyword)) {
                matchingTasks.addTask(task);
            }
        }
        return matchingTasks;
    }

    public boolean hasNoDuplicates(Task task) {
        for (Task t : this.tasks) {
            if (task.equals(t)) {
                return false;
            }
        }
        return true;
    }
}
