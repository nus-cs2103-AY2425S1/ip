package nixy;

import java.util.function.Consumer;

import nixy.exceptions.NixyException;
import nixy.parse.Parsed;
import nixy.parse.Parser;
import nixy.task.Task;
import nixy.task.TaskList;
import nixy.ui.Ui;

/**
 * Todo list application that allows users to manage tasks.
 * Iterative project for CS2103
 */
public class Nixy {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Runnable onExit = () -> {};

    /**
     * Constructor for Nixy.
     * Initializes the user interface, storage, and task list.
     *
     * @param filePath The file path to store tasks data.
     */
    public Nixy(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (NixyException e) {
            ui.showNixyException(e);
            tasks = new TaskList();
        }
    }

    public void setNewDisplay(Consumer<String[]> display) {
        ui.setDisplay(display);
    }

    public void setOnExit(Runnable onExit) {
        this.onExit = onExit;
    }

    public void displayWelcome() {
        ui.showWelcome();
    }

    public void processInput(String userInput) {
        Parsed p;
        Command c;
        try {
            p = Parser.parse(userInput);
            c = p.getCommand();
        } catch (NixyException e) {
            ui.showNixyException(e);
            return;
        }
        switch (c) {
        case BYE:
            ui.showGoodbye();
            onExit.run();
            break;
        case LIST:
            ui.showList(tasks);
            break;
        case MARK:
            try {
                String taskStr = tasks.markTaskAsDone(p.getTaskNumber());
                ui.showMarkedAsDone(taskStr);
                storage.save(tasks);
            } catch (NixyException e) {
                ui.showNixyException(e);
            }
            break;
        case UNMARK:
            try {
                String taskStr = tasks.markTaskAsUndone(p.getTaskNumber());
                ui.showMarkedAsUndone(taskStr);
                storage.save(tasks);
            } catch (NixyException e) {
                ui.showNixyException(e);
            }
            break;
        case DELETE:
            try {
                String taskStr = tasks.deleteTask(p.getTaskNumber());
                ui.showDeletedTask(taskStr, tasks.getTaskCount());
                storage.save(tasks);
            } catch (NixyException e) {
                ui.showNixyException(e);
            }
            break;
        case FIND:
            try {
                TaskList foundTasks = tasks.findTasks(p.getStringParam());
                ui.showMatchingList(foundTasks);
            } catch (NixyException e) {
                ui.showNixyException(e);
            }
            break;
        case TODO:
            // Fallthrough
        case DEADLINE:
            // Fallthrough
        case EVENT:
            try {
                Task task = p.getTask();
                tasks.addTask(task);
                ui.showAddedTask(task, tasks.getTaskCount());
                storage.save(tasks);
            } catch (NixyException e) {
                ui.showNixyException(e);
            }
            break;
        case INVALID:
            ui.showUnknownWarning();
            break;
        default:
            // Should not reach here
            break;
        }
    }

    /**
     * Main driver for Nixy cli
     * Entry point to display welcome message and read user input.
     */
    public void run() {
        // Have to use array to store boolean value to be able to modify it in lambda
        boolean isExit[] = {false};
        onExit = () -> isExit[0] = true;
        displayWelcome();
        while (!isExit[0]) {
            String userInput = ui.readCliInput();
            processInput(userInput);
        }
    }

    public static void main(String[] args) {
        new Nixy("data/tasks.txt").run();
    }
}
