package seedu.task;

import java.util.ArrayList;

import seedu.storage.Storage;
import seedu.ui.Formatter;

/**
 * The {@code TaskList} class manages a list of tasks and provides methods to add, list, mark, unmark,
 * and delete tasks. It also interacts with the UI and storage components to display and save tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();
    private Formatter formatter = new Formatter();

    /**
     * Adds a task to the task list and updates the UI with the new task.
     *
     * @param t The task to be added.
     */
    public String addTask(Task t) {
        this.tasks.add(t);
        return this.formatter.addTaskUi(t, this.tasks.size());
    }

    /**
     * Lists all tasks currently in the task list. If the task list is empty,
     * a message is sent to the UI indicating that there are no tasks.
     */
    public String listTasks() {
        return this.formatter.listTaskUi(this.tasks);
    }

    /**
     * Marks a task as done based on its position in the task list.
     *
     * @param num The index of the task to be marked as done (0-based index).
     */
    public String markTaskAsDone(int num) {
        Task t = this.tasks.get(num);
        t.markAsDone();
        return this.formatter.markTaskAsDoneUi(t);

    }

    /**
     * Unmarks a task as not done based on its position in the task list.
     *
     * @param num The index of the task to be unmarked as not done (0-based index).
     */
    public String unmarkTaskAsDone(int num) {
        Task t = this.tasks.get(num);
        t.markAsNotDone();
        return this.formatter.unmarkTaskAsDoneUi(t);
    }

    /**
     * Deletes a task from the task list based on its position and updates the UI.
     *
     * @param num The index of the task to be deleted (0-based index).
     */
    public String deleteTask(int num) {
        Task t = this.tasks.get(num);
        this.tasks.remove(num);
        return this.formatter.deleteTaskUi(t, this.tasks.size());
    }

    /**
     * Creates a new ToDo task, adds it to the task list, and returns the created task.
     *
     * @param description The description of the ToDo task.
     * @return The created ToDo task.
     */
    public String addToDo(String description) {
        Task t = new ToDo(description);
        return this.addTask(t);
    }

    /**
     * Creates a new Deadline task, adds it to the task list, and returns the created task.
     *
     * @param description The description of the Deadline task.
     * @param end The end date/time of the Deadline task.
     * @return The created Deadline task.
     */
    public String addDeadline(String description, String end) {
        Task t = new Deadline(description, end);
        return this.addTask(t);
    }

    /**
     * Creates a new Event task, adds it to the task list, and returns the created task.
     *
     * @param description The description of the Event task.
     * @param start The start date/time of the Event.
     * @param end The end date/time of the Event.
     * @return The created Event task.
     */
    public String addEvent(String description, String start, String end) {
        Task t = new Event(description, start, end);
        return this.addTask(t);
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

    public String findTasks(String name) {
        ArrayList<Task> temp = new ArrayList<>();
        this.tasks
                .stream()
                .filter((t) -> containsName(t, name))
                .forEach(temp::add);
        return formatter.listTaskUi(temp);
    }

    public boolean containsName(Task t, String name) {
        String taskLowerDescription = t.getDescription().toLowerCase();
        String nameLower = name.toLowerCase();
        return taskLowerDescription.contains(nameLower);
    }
}
