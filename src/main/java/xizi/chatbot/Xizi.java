package xizi.chatbot;//https://nus-cs2103-ay2425s1.github.io/website/admin/standardsAndConventions.html

import xizi.chatbot.command.Command;
import xizi.chatbot.task.Task;
import xizi.chatbot.task.TaskList;

import java.io.IOException;
import java.util.List;

/**
 * The main class for the Xizi chatbot application. This class handles the initialization and
 * running of the chatbot, including loading tasks from a file, parsing user input, and
 * executing commands.
 */
public class Xizi {
    private static final String FILE_PATH = "./data/xizi.txt";
    private final Storage storage;
    private TaskList actions;
    private final Ui ui;
    private final Parser parser;

    /**
     * Constructs a Xizi object with the specified file path for task storage.
     * Initializes the UI, storage, task list, and parser.
     *
     * @param filePath The path of the file where tasks are stored.
     */
    public Xizi(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        actions = new TaskList();
        parser = new Parser();

        try {
            List<Task> loadedTasks = storage.load();
            actions = new TaskList(loadedTasks);
        } catch (IOException | XiziException e) {
            ui.printErrorMessage(e.getMessage());
        }
    }

    /**
     * The main entry point for the Xizi chatbot application.
     * Creates a new instance of Xizi with the default file path and starts the chatbot.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Xizi(FILE_PATH).run();
    }

    /**
     * Runs the Xizi chatbot. Displays the welcome message, then continuously reads
     * user input, parses it into commands, and executes those commands until the
     * user inputs "bye" to exit the application.
     */
    public void run() {
        ui.showWelcomeMessage();
        boolean isRunning = true;
        while (isRunning) {
            try {
                String userInput = ui.readUserInput();
                Command command = parser.parse(userInput);
                command.execute(actions, storage, ui);
                isRunning = !userInput.equals("bye");
            } catch (XiziException | IOException e) {
                ui.printErrorMessage(e.getMessage());
            }
        }
    }
}