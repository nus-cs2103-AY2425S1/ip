import java.io.IOException;

import commands.Command;
import exceptions.InputException;
import parser.Parser;
import storage.Storage;
import storage.TaskList;
import ui.Ui;

/**
 * PandaBot is a simple task management bot that allows users to manage their tasks.
 * It supports operations such as adding, listing, marking, unmarking, deleting tasks, and finding tasks by keywords.
 * The bot can handle different types of tasks including ToDos, Deadlines, and Events.
 */
public class PandaBot {
    /**
     * The storage object responsible for saving and loading tasks from a file.
     */
    private final Storage storage;

    /**
     * The list of tasks managed by PandaBot.
     */
    private final TaskList tasks;

    /**
     * The user interface object responsible for interacting with the user.
     */
    private final Ui ui;

    /**
     * Constructs a new PandaBot with the specified file path for task storage.
     * Initializes the UI, storage, and loads the existing tasks from the specified file.
     *
     * @param filePath the file path where tasks are stored and loaded from.
     */
    public PandaBot(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        TaskList tempTasks;
        try {
            tempTasks = new TaskList(storage.loadTaskList());
        } catch (IOException e) {
            ui.showError("Error loading tasks: " + e.getMessage());
            tempTasks = new TaskList();
        }
        this.tasks = tempTasks;
    }

    /**
     * Starts the PandaBot and enters the main command processing loop.
     * The bot will continue running until the user issues an exit command.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command command = Parser.parse(fullCommand);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (InputException e) {
                ui.showError(e.getMessage());
            } catch (IOException e) {
                ui.showError("Error saving tasks: " + e.getMessage());
            }
        }
        ui.showGoodbye();
    }

    /**
     * The entry point of the PandaBot application.
     * Creates a new PandaBot instance and starts it with the specified task file path.
     *
     * @param args command-line arguments passed to the application (not used).
     */
    public static void main(String[] args) {
        new PandaBot("./data/PandaBot.txt").run();
    }
}
