package conversage;

import conversage.command.ByeCommand;
import conversage.command.Command;
import conversage.exception.ConverSageException;
import conversage.storage.Storage;
import conversage.task.TaskList;
import conversage.ui.Ui;

/**
 * The main class of the ConverSage application.
 */
public class ConverSage {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    public Command lastCommand;
    /**
     * Constructs a ConverSage object with the specified file path for storage.
     *
     * @param filePath the path to the file where tasks are stored.
     */
    public ConverSage(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (ConverSageException e) {
            ui.showError(e.getMessage());
            this.tasks = new TaskList();
        }
    }

    /**
     * Runs the ConverSage application.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (ConverSageException e) {
                ui.showLine();
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * Returns the list of tasks.
     *
     * @return the list of tasks.
     */
    public TaskList getTasks() {
        return tasks;
    }

    /**
     * Returns the UI component.
     *
     * @return the UI component.
     */
    public Ui getUi() {
        return ui;
    }

    /**
     * Returns the storage component.
     *
     * @return the storage component.
     */
    public Storage getStorage() {
        return storage;
    }

    /**
     * Gets the response from ConverSage for the given user input.
     *
     * @param input The user input.
     * @return The response from ConverSage.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            lastCommand = command;
            return command.execute(tasks, ui, storage);
        } catch (ConverSageException e) {
            return e.getMessage();
        }
    }

    /**
     * Checks if the application should exit.
     *
     * @return true if the application should exit, false otherwise.
     */
    public boolean shouldExit() {
        return lastCommand instanceof ByeCommand;
    }

    /**
     * The main method of the ConverSage application.
     *
     * @param args the command-line arguments.
     */
    public static void main(String[] args) {
        new ConverSage("data/tasks.txt").run();
    }



}
