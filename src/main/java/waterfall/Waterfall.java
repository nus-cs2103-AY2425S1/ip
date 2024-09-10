package waterfall;

import java.io.IOException;

import waterfall.command.Command;
import waterfall.task.TaskList;

/**
 * Represents the main class for the Waterfall chatbot application.
 * <p>
 * The <code>Waterfall</code> class handles the initialisation of the application.
 * This includes loading the tasks from storage and starting the user interaction.
 * It manages user command sand interactions by delegating tasks to other components.
 * </p>
 *
 * @author Wai Hong
 */

public class Waterfall {

    private Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Initialises the Waterfall application with the specified storage file path.
     * It sets up the <code>Ui</code> object to be used in next execution.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Waterfall(String filePath) {
        ui = new Ui();
        try {
            storage = new Storage(filePath);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            return;
        }
        try {
            tasks = new TaskList(storage.load());
        } catch (WaterfallException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the waterfall chatbot application.
     * Displays a welcome message and continuously reads user commands until
     * the user decides to exit the application. It handles exceptions that
     * occur during command execution and provides appropriate feedback to the user.
     */
    public void run() {
        ui.showWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (WaterfallException e) {
                ui.showError(e.getMessage());
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * The main method to start the Waterfall application.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        new Waterfall("data/Tasks.txt").run();
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            assert tasks != null : "tasks before command cannot be null";
            assert ui != null : "ui before command cannot be null";
            assert storage != null : "storage before command cannot be null";
            String res = c.execute(tasks, ui, storage);
            return res;
        } catch (WaterfallException e) {
            return e.getMessage();
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
