package lama;

import lama.command.Command;

/**
 * Represent the main entry point of the Lama application.
 * Handles initialisation of components, the main program
 * loop and process user command.
 */
public class Lama {

    private final Storage storage;
    private final Ui ui;
    private TaskList taskList;

    /**
     * Constructs a Lama object with specified file path for storing data.
     * Initialises the UI, storage and task list.
     * If loading task fail, it creates a new one.
     *
     * @param tasksFilePath String file path where tasks will be stored.
     * @param aliasFilePath String file path where alias will be stored.
     */
    public Lama(String tasksFilePath, String aliasFilePath) {
        this.ui = new Ui();
        storage = new Storage(tasksFilePath, aliasFilePath);
        initializeAliasesList();
        this.taskList = initializeTaskList();
    }

    private void initializeAliasesList() {
        try {
            AliasManager.setStorage(storage);
            AliasManager.loadAliases();
        } catch (LamaException e) {
            ui.showError(e.getMessage());
        }
    }
    private TaskList initializeTaskList() {
        try {
            return new TaskList(storage.loadTask());
        } catch (LamaException e) {
            ui.showLoadingError();
            return new TaskList();
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
            isExit = processUserCommand();
        }
    }

    private boolean processUserCommand() {
        try {
            String fullCommand = ui.readCommand();
            Command command = Parser.parse(fullCommand);
            command.run(taskList, storage, ui);
            return command.isExit();
        } catch (LamaException e) {
            ui.showError(e.getMessage());
            return false;
        }
    }
    /**
     * Main method and entry point of the Lama application.
     * Create a new instance of Lama object and run it.
     *
     * @param args not used.
     */
    public static void main(String[] args) {
        new Lama("./data/lama.txt", "./data/alias.txt").run();
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.run(taskList, storage, ui);
        } catch (LamaException e) {
            return e.getMessage();
        }
    }
}
