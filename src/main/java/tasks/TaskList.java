package tasks;

import java.time.LocalDateTime;
import java.util.ArrayList;

import storage.Storage;

/**
 * The {@code TaskList} class manages a list of tasks, providing operations to add, remove,
 * and modify tasks. It supports different types of tasks, including todos, deadlines, and events.
 */
public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Constructs an empty {@code TaskList}.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Loads tasks from the specified storage into this task list.
     *
     * @param storage The storage object from which tasks are retrieved.
     */
    public void loadTasks(Storage storage) {
        this.tasks = storage.retrieveTasks();
    }

    /**
     * Checks if the task list is empty.
     *
     * @return {@code true} if the task list is empty, {@code false} otherwise.
     */
    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The size of the task list.
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Returns the task at the specified index.
     *
     * @param index The index of the task to retrieve (1-based index).
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Marks the task at the specified index as complete.
     *
     * @param index The index of the task to mark as complete (1-based index).
     */
    public void completeTaskInList(int index) {
        this.tasks.get(index - 1).completeTask();
    }

    /**
     * Marks the task at the specified index as incomplete.
     *
     * @param index The index of the task to unmark as incomplete (1-based index).
     */
    public void unmarkTaskInList(int index) {
        this.tasks.get(index - 1).uncompleteTask();
    }

    /**
     * Deletes the task at the specified index from the list.
     *
     * @param index The index of the task to delete (1-based index).
     * @return The task that was removed from the list.
     */
    public Task deleteTaskInList(int index) {
        Task t = this.tasks.get(index - 1);
        this.tasks.remove(index - 1);
        return t;
    }

    /**
     * Adds a new Todo task to the list.
     *
     * @param taskDescription The description of the Todo task to add.
     * @return The newly added Todo task.
     */
    public Todo addTodo(String taskDescription) {
        Todo t = new Todo(taskDescription);
        this.tasks.add(t);
        return t;
    }

    /**
     * Adds a new Deadline task to the list.
     *
     * @param taskDescription The description of the Deadline task to add.
     * @param time            The due date and time of the deadline.
     * @return The newly added Deadline task.
     */
    public Deadline addDeadline(String taskDescription, LocalDateTime time) {
        Deadline d = new Deadline(taskDescription, time);
        this.tasks.add(d);
        return d;
    }

    /**
     * Adds a new Event task to the list.
     *
     * @param taskDescription The description of the Event task to add.
     * @param startTime       The start time of the event.
     * @param endTime         The end time of the event.
     * @return The newly added Event task.
     */
    public Event addEvent(String taskDescription, LocalDateTime startTime, LocalDateTime endTime) {
        Event e = new Event(taskDescription, startTime, endTime);
        this.tasks.add(e);
        return e;
    }
}
