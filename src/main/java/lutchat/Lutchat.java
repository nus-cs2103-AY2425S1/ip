package lutchat;

public class Lutchat {
    private final Ui ui;
    private final Storage storage;
    private TaskList tasks;

    public Lutchat(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showError("Error Loading Tasks... " + e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Generates a response for the user's input string.
     * Handles the input and returns a string response instead of printing to console.
     *
     * @param input The user's input string.
     * @return The application's response string.
     */
    public String getResponse(String input) {
        boolean isRunning = Parser.parse(input, ui, tasks, storage);
        if (!isRunning) {
            return ui.exit();
        }
        storage.save(tasks);
        return ui.getLastResponse();
    }
}
