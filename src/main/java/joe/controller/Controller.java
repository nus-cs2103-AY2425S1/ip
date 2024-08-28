package joe.controller;

import java.util.ArrayList;

import joe.storage.Storage;
import joe.task.Task;
import joe.task.TaskDeadline;
import joe.task.TaskEvent;
import joe.task.TaskTodo;
import joe.ui.Ui;

public class Controller {
    private final Ui ui;
    private final Storage storage;
    protected ArrayList<Task> store;

    public Controller(Ui ui) {
        this.ui = ui;
        this.storage = new Storage();
        this.store = storage.loadTasks();
    }

    public void startProgram() {
        ui.printHelp();
        ui.greet();
    }

    public void endProgram() {
        storage.saveTasks(store);
        ui.farewell();
    }

    public void handleList() {
        ui.printListMessage(store);
    }

    public void handleHelp() {
        ui.printHelp();
    }

    public void handleDone(int index) {
        store.get(index).toggleDone();
        ui.printDoneMessage(store.get(index));
    }

    public void handleUndone(int index) {
        store.get(index).toggleDone();
        ui.printUndoneMessage(store.get(index));
    }

    public void handleDelete(int index) {
        Task deletedTask = store.get(index);
        store.remove(index);
        ui.printDeleteMessage(deletedTask, store.size());
    }

    public void handleTodo(String task) {
        if (task.equals("")) {
            ui.printEmptyTaskErrorMessage();
            return;
        }
        store.add(new TaskTodo(task));
        ui.printTodoMessage(store.get(store.size() - 1), store.size());
    }

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
}
