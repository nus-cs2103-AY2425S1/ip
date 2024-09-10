package assistinator;

/**
 * Main application
 */
public class Assistinator {
    private final Storage storage;
    private final Ui ui;
    private TaskList tasks;
    private final CommandExecutor commandExecutor;

    /**
     * Initialising an Assistinator class
     * @param filePath path to storage file
     */
    public Assistinator(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (AssitinatorExceptions e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
        commandExecutor = new CommandExecutor(ui, tasks, storage);
    }

    /**
     * Runs the application
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
            } catch (AssitinatorExceptions e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public String getResponse(String input) {
        try {
            Command command = commandExecutor.parseCommand(input);
            String response = commandExecutor.executeCommand(command, input);
            assert response != null : "Response should not be null";
            return response;
        } catch (AssitinatorExceptions e) {
            return e.getMessage();
        }
    }

    public static void main(String[] args) {
        new Assistinator("./data/assistinator.txt").run();
    }
}
