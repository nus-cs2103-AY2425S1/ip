package components;

import command.Command;
import exceptions.LightException;
import task.TaskList;

import java.util.NoSuchElementException;

/**
 * Represents the main class of the components.Light program.
 */
public class Light {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates a components.Light object.
     *
     * @param filePath The file path of the storage file.
     */
    public Light(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(storage.load());
    }

    /**
     * The main method of the components.Light program.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        Light program = new Light("./data/saved.txt");
        program.run();
    }

    /**
     * Runs the Light program.
     */
    public void run() {
        ui.welcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command command = Parser.parse(fullCommand);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (LightException e) {
                ui.showError(e);
            } catch (NoSuchElementException e) {
                ui.closeUi();
                return;
            }
        }
    }

    /**
     * Gets the response from the Light program.
     *
     * @param input The input to the Light program.
     * @return The response from the Light program.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            String response = command.execute(tasks, ui, storage);
            return response;
        } catch (LightException e) {
            return e.toString();
        }
    }
}

