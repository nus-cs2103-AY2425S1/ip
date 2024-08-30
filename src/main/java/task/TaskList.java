package task;

import storage.Storage;

import ui.Ui;

import java.util.ArrayList;

/**
 * The {@code TaskList} class manages a list of tasks and provides methods to add, list, mark, unmark,
 * and delete tasks. It also interacts with the UI and storage components to display and save tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();
    private Ui ui = new Ui();

    /**
     * Adds a task to the task list and updates the UI with the new task.
     *
     * @param t The task to be added.
     */
    public void addTask(Task t) {
        this.tasks.add(t);
        this.ui.addTaskUi(t, this.tasks.size());
    }

    /**
     * Lists all tasks currently in the task list. If the task list is empty,
     * a message is sent to the UI indicating that there are no tasks.
     */
    public void listTasks() {
        if (this.tasks.isEmpty()) {
            this.ui.sendMessage("No items yet!");
        } else {
            for (int i = 0; i < this.tasks.size(); i++) {
                this.ui.sendMessage((i + 1) + ". " + this.tasks.get(i));
            }
        }
    }

    /**
     * Marks a task as done based on its position in the task list.
     *
     * @param num The index of the task to be marked as done (0-based index).
     */
    public void markTask(int num) {
        Task t = this.tasks.get(num);
        t.markAsDone();
        this.ui.markTaskUi(t);
    }

    /**
     * Unmarks a task as not done based on its position in the task list.
     *
     * @param num The index of the task to be unmarked as not done (0-based index).
     */
    public void unmarkTask(int num) {
        Task t = this.tasks.get(num);
        t.markAsNotDone();
        this.ui.unmarkTaskUi(t);
    }

    /**
     * Deletes a task from the task list based on its position and updates the UI.
     *
     * @param num The index of the task to be deleted (0-based index).
     */
    public void deleteTask(int num) {
        Task t = this.tasks.get(num);
        this.tasks.remove(num);
        this.ui.deleteTaskUi(t, this.tasks.size());
    }

    /**
     * Creates a new ToDo task, adds it to the task list, and returns the created task.
     *
     * @param description The description of the ToDo task.
     * @return The created ToDo task.
     */
    public Task addToDo(String description) {
        Task t = new ToDo(description);
        this.addTask(t);
        return t;
    }

    /**
     * Creates a new Deadline task, adds it to the task list, and returns the created task.
     *
     * @param description The description of the Deadline task.
     * @param end The end date/time of the Deadline task.
     * @return The created Deadline task.
     */
    public Task addDeadline(String description, String end) {
        Task t = new Deadline(description, end);
        this.addTask(t);
        return t;
    }

    /**
     * Creates a new Event task, adds it to the task list, and returns the created task.
     *
     * @param description The description of the Event task.
     * @param start The start date/time of the Event.
     * @param end The end date/time of the Event.
     * @return The created Event task.
     */
    public Task addEvent(String description, String start, String end) {
        Task t = new Event(description, start, end);
        this.addTask(t);
        return t;
    }

    /**
     * Saves all tasks in the task list to the provided storage.
     *
     * @param s The storage where tasks will be saved.
     */
    public void saveTask(Storage s) {
        s.prepareSave();
        for (Task t: this.tasks) {
            s.saveTask(t.toSave());
        }
    }
}
