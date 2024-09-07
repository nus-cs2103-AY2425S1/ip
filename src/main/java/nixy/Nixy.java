package nixy;

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

    public String processInput(String userInput) {
        return "Not implemented yet";
    }

    /**
     * Main driver for Nixy cli
     * Entry point to display welcome message and read user input.
     */
    public void run() {
        ui.showWelcome();
        Boolean isExit = false;
        while (!isExit) {
            String input = ui.readInput();
            Parsed p;
            Command c;
            try {
                p = Parser.parse(input);
                c = p.getCommand();
            } catch (NixyException e) {
                ui.showNixyException(e);
                continue;
            }
            switch (c) {
            case BYE:
                isExit = true;
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
        ui.showGoodbye();
    }

    public static void main(String[] args) {
        new Nixy("data/tasks.txt").run();
    }
}
