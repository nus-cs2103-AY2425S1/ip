package joe.controller;

import java.util.ArrayList;

import joe.storage.Storage;
import joe.task.Task;
import joe.task.TaskDeadline;
import joe.task.TaskEvent;
import joe.task.TaskTodo;
import joe.ui.Ui;

/**
 * Provides the main logic of the program. Handles user commands and updates the
 * task store.
 */
public class Controller {
    private final Ui ui;
    private final Storage storage;
    protected ArrayList<Task> store;

    public Controller(Ui ui) {
        this.ui = ui;
        this.storage = new Storage();
        this.store = storage.loadTasks();
    }

    /**
     * Starts the program and prints the greeting message.
     */
    public void startProgram() {
        ui.printHelp();
        ui.greet();
    }

    /**
     * Ends the program and prints the farewell message.
     */
    public void endProgram() {
        storage.saveTasks(store);
        ui.farewell();
        ui.stop();
    }

    /**
     * Handles list command.
     */
    public void handleList() {
        ui.printListMessage(store);
    }

    /**
     * Handles help command.
     */
    public void handleHelp() {
        ui.printHelp();
    }

    /**
     * Handles mark command.
     * Toggles the done status of the task at the specified index.
     * 
     * @param index The index of the task to be marked as done.
     * 
     */
    public void handleDone(int index) {
        assert store.get(index) != null : "Task does not exist";
        if (index < 0 || index >= store.size()) {
            ui.printInvalidIndexErrorMessage();
            return;
        }
        store.get(index).toggleDone();
        ui.printDoneMessage(store.get(index));
    }

    /**
     * Handles unmark command.
     * Toggles the done status of the task at the specified index.
     * 
     * @param index The index of the task to be marked as undone.
     * 
     */
    public void handleUndone(int index) {
        if (index < 0 || index >= store.size()) {
            ui.printInvalidIndexErrorMessage();
            return;
        }
        store.get(index).toggleDone();
        ui.printUndoneMessage(store.get(index));
    }

    /**
     * Handles delete command.
     * Deletes the task at the specified index from the store.
     * 
     * @param index The index of the task to be deleted.
     * 
     */
    public void handleDelete(int index) {
        if (index < 0 || index >= store.size()) {
            ui.printInvalidIndexErrorMessage();
            return;
        }
        Task deletedTask = store.get(index);
        store.remove(index);
        ui.printDeleteMessage(deletedTask, store.size());
    }

    /**
     * Handles todo command.
     * Creates a new todo task and adds it to the store.
     * Prints an error message if the task is empty.
     * 
     * @param task The task description.
     * 
     */
    public void handleTodo(String task) {
        if (task.equals("")) {
            ui.printEmptyTaskErrorMessage();
            return;
        }
        store.add(new TaskTodo(task));
        ui.printTodoMessage(store.get(store.size() - 1), store.size());
    }

    /**
     * Handles deadline command.
     * Creates a new deadline task and adds it to the store.
     * Prints an error message if the task is empty or the date format is invalid.
     * 
     * @param task The task description.
     * @param by The deadline date.
     * 
     */
    public void handleDeadline(String task, String by) {
        if (task.equals("")) {
            ui.printEmptyTaskErrorMessage();
            return;
        }

        if (!TaskDeadline.isValidFormat(by)) {
            ui.printInvalidDateFormatErrorMessage();
            return;
        }
        store.add(new TaskDeadline(task, by));
        ui.printDeadlineMessage(store.get(store.size() - 1), store.size());
    }

    /**
     * Handles event command.
     * Creates a new event task and adds it to the store.
     * Prints an error message if the task is empty or the date format is invalid.
     * 
     * @param task The task description.
     * @param from The start date of the event.
     * @param to The end date of the event.
     * 
     */
    public void handleEvent(String task, String from, String to) {
        if (task.equals("")) {
            ui.printEmptyTaskErrorMessage();
            return;
        }

        if (!TaskEvent.isValidFormat(from, to)) {
            ui.printInvalidDateFormatErrorMessage();
            return;
        }

        store.add(new TaskEvent(task, from, to));
        ui.printEventMessage(store.get(store.size() - 1), store.size());
    }

    /**
     * Handles find command.
     * Prints all tasks that contain the keyword.
     * 
     * @param keyword The keyword to search for.
     * 
     */
    public void handleFind(String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task : store) {
            if (task.getTask().contains(keyword)) {
                foundTasks.add(task);
            }
        }
        ui.printFindMessage(foundTasks);
    }

    /**
     * Handles postpone command.
     * Postpones the task at the specified index by the specified number of days.
     * 
     * @param index The index of the task to be postponed.
     * @param days The number of days to postpone the task by.
     */
    public void handlePostpone(int index, int days) {
        if (index < 0 || index >= store.size()) {
            ui.printInvalidIndexErrorMessage();
            return;
        }
        Task task = store.get(index);
        if (task instanceof TaskDeadline) {
            TaskDeadline deadline = (TaskDeadline) task;
            deadline.postponeDeadline(days);
            ui.printPostponeMessage(deadline, days);
        } else if (task instanceof TaskEvent) {
            TaskEvent event = (TaskEvent) task;
            event.postponeEvent(days);
            ui.printPostponeMessage(event, days);
        } else {
            ui.printPostponeErrorMessage();
        }
    }
}
