package assistinator;

import assistinator.commands.ByeCommand;
import assistinator.commands.Command;

/**
 * Main application.
 */
public class Assistinator {
    private final Storage storage;
    private final Ui ui;
    private TaskList tasks;
    private Parser parser;
    private boolean isError;

    /**
     * Initialises an Assistinator class.
     * @param filePath Path to storage file.
     */
    public Assistinator(String filePath) {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (AssistinatorException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
        isError = false;
    }

    /**
     * Runs the application.
     */
    public void run() {
        ui.showWelcome();
        boolean isRunning = true;
        while (isRunning) {
            try {
                String input = ui.readCommand();
                Command command = parser.parseCommand(input);
                String response = command.execute(tasks, storage);
                assert response != null : "Response should not be null";
                ui.showResponse(response);
                if (command instanceof ByeCommand) {
                    isRunning = false;
                }
            } catch (AssistinatorException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * Gets response for GUI.
     * @param input User input.
     * @return Response to user.
     */
    public String getResponse(String input) {
        try {
            Command command = parser.parseCommand(input);
            isError = false;
            String response = command.execute(tasks, storage);
            assert response != null : "Response should not be null";
            return response;
        } catch (AssistinatorException e) {
            isError = true;
            return e.getMessage();
        }
    }

    /**
     * Checks if an error has occurred.
     * @return True if an error has occurred, otherwise false.
     */
    public boolean isError() {
        return isError;
    }

    public static void main(String[] args) {
        new Assistinator("./data/assistinator.txt").run();
    }
}
