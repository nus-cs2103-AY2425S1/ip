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

    public TaskList getTasks() {
        return tasks;
    }

    public Ui getUi() {
        return ui;
    }

    public Storage getStorage() {
        return storage;
    }
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
