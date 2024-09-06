package main;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Manages a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Initializes an empty TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Initializes a TaskList with an existing list of tasks.
     *
     * @param tasks The initial list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a new Todo task to the list.
     *
     * @param description The description of the Todo task.
     * @return The newly added Todo task.
     */
    public Todo addTodo(String description) {
        Todo t = new Todo(description);
        this.tasks.add(t);
        return t;
    }

    /**
     * Removes a task from the list.
     *
     * @param i The index of the task to be removed.
     * @return The removed task.
     */
    public Task removeTask(int i) {
        return this.tasks.remove(i);
    }

    /**
     * Gets the number of tasks in the list.
     *
     * @return The number of tasks.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Retrieves a task from the list.
     *
     * @param i The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task get(int i) {
        return tasks.get(i);
    }

    /**
     * Adds a new Deadline task to the list.
     *
     * @param description The description of the Deadline task.
     * @param deadline    The deadline of the task.
     * @return The newly added Deadline task.
     */
    public Deadline addDeadline(String description, LocalDateTime deadline) {
        Deadline d = new Deadline(description, deadline);
        this.tasks.add(d);
        return d;
    }

    /**
     * Adds a new Event task to the list.
     *
     * @param description The description of the Event task.
     * @param from        The start time of the event.
     * @param to          The end time of the event.
     * @return The newly added Event task.
     */
    public Event addEvent(String description, LocalDateTime from, LocalDateTime to) {
        Event e = new Event(description, from, to);
        this.tasks.add(e);
        return e;
    }

    /**
     * Checks if the task list is empty.
     *
     * @return True if the list is empty, otherwise false.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }
}
