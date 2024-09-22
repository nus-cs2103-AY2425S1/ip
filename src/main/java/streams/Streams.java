package streams;

import streams.command.Command;
import streams.exception.StreamsException;
import streams.task.TaskList;
import streams.util.Parser;
import streams.util.Storage;
import streams.util.Ui;
/**
 * Main class for the Streams task management application.
 */
public class Streams {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Constructs the main Duke application.
     *
     * @param filePath The file path for storing tasks.
     */
    public Streams(String filePath) {
        assert filePath != null && !filePath.isEmpty() : "File path cannot be null or empty";
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (StreamsException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }
    /**
     * Runs the main loop of the application.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (StreamsException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }
    /**
     * Main method to start the application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        new Streams("data/saveFile.txt").run();
    }

    /**
     * Processes a single command and returns the response.
     *
     * @param input The user's input command.
     * @return The response from executing the command.
     */
    public String getResponse(String input) {
        assert input != null && !input.isEmpty() : "Input cannot be null or empty";
        StringBuilder response = new StringBuilder();
        try {
            Command c = Parser.parse(input);

            // Create a custom Ui that captures output
            Ui captureUi = new Ui() {
                @Override
                public void showMessage(String message) {
                    response.append(message).append("\n");
                }
                @Override
                public void showError(String message) {
                    response.append("Error: ").append(message).append("\n");
                }
            };
            c.execute(tasks, captureUi, storage);

            if (c.isExit()) {
                response.append("Exiting Streams. Goodbye!");
            }
        } catch (StreamsException e) {
            response.append("Error: ").append(e.getMessage());
        }
        return response.toString().trim();
    }
}
