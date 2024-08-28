package lama;

import lama.command.Command;

/**
 * Represent the main entry point of the Lama application.
 * Handles initialisation of components, the main program
 * loop and process user command.
 */
public class Lama {

    private Storage storage;
    private Ui ui;
    private TaskList taskList;

    /**
     * Construct a Lama object with specified file path for storing data.
     * Initialise the UI, storage and task list.
     * If loading task fail, it creates a new one.
     *
     * @param filePath String file path where tasks will be stored.
     */
    public Lama(String filePath) {
        this.ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.loadTask());
        } catch (LamaException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    /**
     * Runs the main loop of the Lama application.
     * Displays a welcome message and continuously processes user commands
     * until the user issues an exit command.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.run(taskList, storage, ui);
                isExit = c.isExit();
            } catch (LamaException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * Main method and entry point of the Lama application.
     * Create a new instance of Lama object and run it.
     *
     * @param args not used.
     */
    public static void main(String[] args) {
        new Lama("./data/lama.txt").run();
    }
}
