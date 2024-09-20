package sammy;

import sammy.command.Command;
import sammy.task.Task;
import sammy.task.TaskList;

import java.io.IOException;

/**
 * The main class for the Sammy application. Initializes the necessary components such as storage,
 * task list, and user interface, and handles the main program loop.
 */public class Sammy {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Initializes the Sammy application by setting up the storage, task list, and user interface.
     *
     * @param filePath The file path where tasks are stored.
     */
    public Sammy(String filePath) {
        assert filePath != null && !filePath.isEmpty() : "File path cannot be null or empty";
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showErrorMessage("Error loading tasks from file");
            tasks = new TaskList();
        }
        assert tasks != null : "TaskList must be initialized";
        assert storage != null : "Storage must be initialized";
        assert ui != null : "UI must be initialized";
    }

    public String getResponse(String input) {
        assert input != null && !input.isEmpty() : "Input cannot be null or empty";
        try {
            String line = ui.showLine();
            Command command = Parser.parse(input);
            return line + command.execute(tasks, ui, storage) + line;
        } catch (SammyException | IOException e) {
            return ui.showErrorMessage(e.getMessage());
        }
    }

    /**
     * Runs the main loop of the Sammy application. Continually reads user commands, executes them,
     * and handles exceptions until the exit command is received.
     */
    public void run() {
        ui.showWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                assert fullCommand != null && !fullCommand.isEmpty() : "Command cannot be null or empty";
                ui.showLine();
                Command command = Parser.parse(fullCommand);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (SammyException | IOException e) {
                ui.showErrorMessage(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }
}



