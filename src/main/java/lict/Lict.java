package lict;

import lict.command.Command;
/**
 * The {@code Lict} class represents the main application and flow for the program.
 * It handles the initialization, execution, and termination of the application.
 */

public class Lict {
    private static final String DEFAULT_FILE_PATH = "data/LictData.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a {@code Lict} object and initializes the UI, storage, and task list.
     * It loads tasks from the specified file path.
     *
     * @param filePath The file path where the task data is stored.
     */
    public Lict(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (LictException e) {
            ui.showError(e.getMessage());
        }
    }

    public Lict() {
        this(DEFAULT_FILE_PATH);
    }
    /**
     * Starts the Lict application, handling user interactions in a loop until the user chooses to exit.
     */
    public void run() {
        ui.startConversation();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (LictException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Lict("data/LictData.txt").run();
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
            return ui.getLastOutput();
        } catch (LictException e) {
            return e.getMessage();
        }
    }

}
