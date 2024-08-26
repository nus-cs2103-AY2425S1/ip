package neko.task;
import neko.NekoException;
import neko.Storage;
import neko.ui.Ui;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * The TaskList class represents a task list that stores tasks added by the user,
 * using an ArrayList to manage the tasks.
 *
 * <p>It provides functionalities to add, list, delete, and mark/unmark tasks in the list.
 * Tasks can be of different types, such as Todo, Deadline, or Event.</p>
 *
 * @author  Siow Rui Ming
 * @version 0.1
 * @since   2024-08-19
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs a TaskList object with an existing list of tasks.
     *
     * @param tasks The initial list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Prompts the user to add a task based on the task type and updates the storage.
     *
     * @param taskType The type of task to be added ("1" for Todo, "2" for Deadline, "3" for Event).
     * @param ui The user interface for interaction with the user.
     * @param storage The storage system for saving the task details.
     * @throws IOException If an I/O error occurs while writing to storage.
     */
    public void addTask(String taskType, Ui ui, Storage storage) throws IOException {
        Task task = null;
        switch (taskType) {
        case "1":
            task = addTodoTask(ui, storage);
            break;
        case "2":
            task = addDeadlineTask(ui, storage);
            break;
        case "3":
            task = addEventTask(ui, storage);
            break;
        default:
            ui.showMessage("Oops /ᐠ > ˕ <マ, that's not a valid option meow! Please enter 1, 2, or 3 meow!");
            break;
        }
        if (task != null) {
            tasks.add(task);
            ui.showMessage("Purrfect! I've added this task meow ฅ/ᐠᓀ ﻌ ᓂマ\n "
                    + task + "\nNow you have " + tasks.size() + " tasks in your list meow");
        }
    }

    /**
     * Adds a Todo task based on user input and updates the storage.
     *
     * @param ui The user interface for interacting with the user.
     * @param storage The storage system for saving the task details.
     * @return The created Todo task.
     * @throws IOException If an I/O error occurs while writing to storage.
     */
    private Task addTodoTask(Ui ui, Storage storage) throws IOException {
        String taskName = ui.getTaskName();
        Task task = new Todo(taskName);
        storage.writeFile("T | 0 | " + taskName + "\n");
        ui.showMessage("");
        return task;
    }

    /**
     * Adds a Deadline task based on user input and updates the storage.
     *
     * @param ui The user interface for interacting with the user.
     * @param storage The storage system for saving the task details.
     * @return The created Todo task.
     * @throws IOException If an I/O error occurs while writing to storage.
     */
    private Task addDeadlineTask(Ui ui, Storage storage) throws IOException {
        String taskName = ui.getTaskName();
        LocalDateTime deadline = ui.getDateTime("Enter the deadline date and time in the form 'yyyy-MM-ddTHH:mm' meow:");
        Task task = new Deadline(taskName, deadline);
        storage.writeFile("D | 0 | " + taskName + " | " + task.getTime() + "\n");
        return task;
    }

    /**
     * Adds an Event task based on user input and updates the storage.
     *
     * @param ui The user interface for interacting with the user.
     * @param storage The storage system for saving the task details.
     * @return The created Todo task.
     * @throws IOException If an I/O error occurs while writing to storage.
     */
    private Task addEventTask(Ui ui, Storage storage) throws IOException {
        String taskName = ui.getTaskName();
        LocalDateTime startDateTime = ui.getDateTime("Enter the start date and time in the form 'yyyy-MM-ddTHH:mm' meow:");
        LocalDateTime endDateTime = ui.getDateTime("Enter the end date and time in the form 'yyyy-MM-ddTHH:mm' meow:");

        while (endDateTime.isBefore(startDateTime)) {
            ui.showMessage("End time cannot be before start time meow! Please enter the end date and time again");
            endDateTime = ui.getDateTime("Enter the end date and time in the form 'yyyy-MM-ddTHH:mm' meow:");
        }

        Task task = new Event(taskName, startDateTime, endDateTime);
        storage.writeFile("E | 0 | " + taskName + " | " + task.getTime() + "\n");
        return task;
    }

    /**
     * Retrieves the task at the specified index.
     *
     * @param index The index of the task.
     * @return The task stored at the index.
     * @throws NekoException If the index is invalid.
     */
    public Task getTask(int index) throws NekoException {
        checkValidIndex(index);
        return tasks.get(index);
    }

    /**
     * Returns the size of the task list.
     *
     * @return The size of the task list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Outputs the string representation of the tasks in the list if there is
     * at least one task in the list. Otherwise, output an error message.
     *
     * @param ui The user interface for interacting with the user.
     */
    public void listTasks(Ui ui) {
        if (tasks.isEmpty()) {
            ui.showMessage("You don't have any tasks yet meow!");
            return;
        }
        ui.showMessage("Here are the tasks in your list meow:");
        for (int i = 0; i < tasks.size(); i++) {
            ui.showMessageWithoutDivider((i + 1) + ". " + tasks.get(i));
        }
    }

    /**
     * Marks the task at the given index as done if it's not marked as done yet.
     * Otherwise, simply return null (if the task is already marked as done).
     *
     * @param index The position where the task is stored.
     * @return The task after marking as done, else return null.
     * @throws NekoException If the index is invalid.
     */
    public Task markTask(int index) throws NekoException {
        checkValidIndex(index);
        if (tasks.get(index).markAsDone()) {
            return tasks.get(index);
        } else {
            return null;
        }
    }

    /**
     * Marks the task at the given index as not done if it's marked as done.
     * Otherwise, simply return null (if the task is already marked as not done).
     *
     * @param index The position where the task is stored.
     * @return The task after marking as not done, else return null.
     * @throws NekoException If the index is invalid.
     */
    public Task unmarkTask(int index) throws NekoException {
        checkValidIndex(index);
        if (tasks.get(index).markAsNotDone()) {
            return tasks.get(index);
        } else {
            return null;
        }
    }

    /**
     * Checks if the index is valid, i.e. greater or equal to 0, smaller than the
     * size of the list, and whether the task is empty, and throw an exception
     * with the corresponding message.
     * @param index The index where the task is stored in the list.
     * @throws NekoException If the index is invalid.
     */
    protected void checkValidIndex(int index) throws NekoException {
        if (index < 0)  {
            throw new NekoException("Invalid task number meow!");
        }
        if (tasks.isEmpty())  {
            throw new NekoException("You don't have any tasks yet meow!");
        }
        if (index >= tasks.size()) {
            throw new NekoException("You only have " + tasks.size() + " tasks now meow!");
        }
    }

    /**
     * Deletes the task at the given index if the index is valid.
     * Otherwise, do nothing.
     *
     * @param index The position where the task is stored.
     * @throws NekoException If the index is invalid.
     */
    public void deleteTask(int index) throws NekoException {
        checkValidIndex(index);
        tasks.remove(index);
    }
}