package mittens;

import javafx.application.Application;
import mittens.commands.Command;
import mittens.parser.BadInputException;
import mittens.parser.CommandParser;
import mittens.storage.Storage;
import mittens.storage.StorageFileException;
import mittens.task.TaskList;
import mittens.ui.TextUi;
import mittens.ui.Ui;
import mittens.ui.fx.MainApp;

/**
 * Represents the main class where the program is initialized and the main loop is run.
 */
public class Mittens {
    public static final boolean IS_USING_GUI = true;
    public static final String DEFAULT_STORAGE_FILE_PATH = "data/data.txt";

    private final Ui ui;
    private final Storage storage;
    private TaskList taskList;

    /**
     * Creates a new Mittens object with the specified UI object and storage file path.
     *
     * @param ui The UI object
     * @param storageFilePath The file path to the storage file
     */
    public Mittens(Ui ui, String storageFilePath) {
        this.ui = ui;
        this.storage = new Storage(storageFilePath);

        this.loadTaskList();
    }

    /**
     * Initializes the task list by loading it from the storage file.
     */
    public void loadTaskList() {
        TaskList temp;
        try {
            temp = storage.load();
        } catch (StorageFileException e) {
            ui.showErrorMessage(e);
            ui.showRegularMessage("Would you like to continue with a new list instead? (y/n)");
            if (ui.getUserInput().equals("y")) {
                temp = new TaskList();
            } else {
                throw new RuntimeException("User chose to exit the program");
            }
        } catch (Exception e) {
            InitializationException newException = new InitializationException(e.getMessage());
            ui.showErrorMessage(newException);
            throw new RuntimeException("Error occurred during initialization");
        }

        this.taskList = temp;
    }

    /**
     * Parses the given input and executes the command, then returns a
     * boolean indicating whether the program should exit.
     *
     * @param input The input to process
     * @return A boolean indicating whether the program should exit
     */
    public boolean process(String input) {
        CommandParser commandParser = new CommandParser();
        try {
            Command command = commandParser.parse(input);
            command.execute(this.taskList, this.ui, this.storage);
            return command.isExit();
        } catch (BadInputException e) {
            ui.showErrorMessage(e);
            return false;
        }
    }

    /**
     * Runs the main loop of the program which accepts user input.
     */
    public void run() {
        if (taskList == null) {
            throw new RuntimeException("Task list is not initialized");
        }

        CommandParser commandParser = new CommandParser();

        ui.showGreetingMessage();

        while (true) {
            String input = ui.getUserInput();
            boolean shouldExit = process(input);
            if (shouldExit) {
                break;
            }
        }

    }

    public static void main(String[] args) {
        if (IS_USING_GUI) {
            Application.launch(MainApp.class);
        } else {
            Mittens mittens = new Mittens(new TextUi(), DEFAULT_STORAGE_FILE_PATH);
            mittens.run();
        }

    }
}
