package assistinator;

/**
 * Main application.
 */
public class Assistinator {
    private final Storage storage;
    private final Ui ui;
    private TaskList tasks;
    private final CommandExecutor commandExecutor;
    private boolean isError;

    /**
     * Initialises an Assistinator class.
     * @param filePath Path to storage file.
     */
    public Assistinator(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (AssitinatorException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
        commandExecutor = new CommandExecutor(ui, tasks, storage);
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
                String fullCommand = ui.readCommand();
                Command command = commandExecutor.parseCommand(fullCommand);
                String response = commandExecutor.executeCommand(command, fullCommand);
                assert response != null : "Response should not be null";
                ui.showResponse(response);
                if (command == Command.BYE) {
                    isRunning = false;
                }
            } catch (AssitinatorException e) {
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
            Command command = commandExecutor.parseCommand(input);
            String response = commandExecutor.executeCommand(command, input);
            isError = false;
            assert response != null : "Response should not be null";
            return response;
        } catch (AssitinatorException e) {
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
