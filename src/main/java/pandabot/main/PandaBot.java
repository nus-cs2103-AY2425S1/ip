package pandabot.main;

import java.io.IOException;

import pandabot.commands.Command;
import pandabot.exceptions.InputException;
import pandabot.parser.Parser;
import pandabot.storage.Storage;
import pandabot.storage.TaskList;
import pandabot.ui.Ui;

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
        assert filePath != null : "File path for task storage should not be null.";
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
     * Generates a response for the user's chat message.
     * This method parses the user's input, executes the corresponding command,
     * and returns the result as a response message.
     *
     * @param input The user's input message that contains a command for PandaBot to execute.
     * @return The response message after processing the user's input.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(tasks, ui, storage);
        } catch (InputException e) {
            return e.getMessage();
        } catch (IOException e) {
            return "Error saving tasks: " + e.getMessage();
        }
    }
}
