package arsenbot;

import arsenbot.command.Command;
import arsenbot.command.Parser;
import arsenbot.storage.Storage;
import arsenbot.task.Task;
import arsenbot.task.TaskList;
import arsenbot.task.TaskManagerException;
import arsenbot.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The main class of the ArsenBot application.
 * ArsenBot manages tasks such as ToDo, Deadline, and Event.
 * It interacts with the user through a command-line interface, handles task storage,
 * and processes user commands.
 */
public class ArsenBot {

    private final Ui ui;
    private final TaskList tasks;
    private final Storage storage;

    /**
     * Constructs an ArsenBot object with the specified file path for task storage.
     * Loads tasks from the file if available, otherwise initializes an empty task list.
     *
     * @param filePath the path to the file where tasks are stored
     */
    public ArsenBot(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(loadTasksFromFile());
    }

    /**
     * Loads tasks from the file, handling any potential IO errors.
     *
     * @return a list of tasks loaded from the file or an empty list if file load fails
     */
    private List<Task> loadTasksFromFile() {
        try {
            return storage.load();
        } catch (IOException e) {
            ui.showError("Error: Unable to load tasks from file.");
            return new ArrayList<>();
        }
    }

    /**
     * Starts the ArsenBot application, displaying a welcome message and processing
     * user commands in a loop until the exit command is given.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.readCommand();
                Command command = Parser.parse(input);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (TaskManagerException e) {
                ui.showError("TaskManager error: " + e.getMessage());
            } catch (IOException e) {
                ui.showError("I/O error: " + e.getMessage());
            }
            ui.showLine();
        }
    }

    /**
     * Generates a response for the user's chat message.
     * Processes the command and returns a response string.
     *
     * @param input the user's input command
     * @return a response message based on the command execution
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(tasks, ui, storage);
        } catch (TaskManagerException e) {
            return ui.showError("TaskManager error: " + e.getMessage());
        } catch (IOException e) {
            return ui.showError("I/O error: " + e.getMessage());
        }
    }

    /**
     * The entry point of the ArsenBot application.
     * Initializes ArsenBot with a specified file path for task history and starts the bot.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        new ArsenBot("./data/history.txt").run();
    }
}
