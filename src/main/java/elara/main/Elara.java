package elara.main;

import elara.command.Command;
import elara.command.ExitCommand;
import elara.task.InvalidInputException;
import elara.utils.Parser;
import elara.utils.Storage;
import elara.utils.TaskList;
import elara.utils.Ui;

/**
 * The main class for the Elara chatbot application.
 * This class manages the overall program flow, handling user input,
 * executing commands, and interacting with the task list and storage.
 */
public class Elara {

    private static final String FILE_PATH = "./data/Elara.txt";
    private final Storage storage;
    private final TaskList taskList;
    private final Ui ui;

    /**
     * Constructs an instance of the Elara chatbot.
     *
     * @param filePath The file path where the task list is stored.
     */
    public Elara(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        taskList = new TaskList(storage.load());
    }

    /**
     * Constructs an instance of the Elara chatbot.
     * Default where file to load for Storage instance is defined as FILE_PATH
     */
    public Elara() {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        taskList = new TaskList(storage.load());
    }

    /**
     * Starts the chatbot and processes user input in a loop until an ExitCommand is given.
     * The method reads user input, parses it into a command and executes the command.
     */
    public void run() {
        ui.showWelcomeMessage();
        while (true) {
            String input = ui.readInput();

            try {
                Command command = Parser.parse(input);
                command.execute(taskList, ui, storage);
                if (command instanceof ExitCommand) {
                    break;
                }
            } catch (InvalidInputException e) {
                ui.showInvalidCommandMessage(e);
            }
        }
    }

    /**
     * Gets response from Elara chatbot
     *
     * @param input input string.
     * @return response string from Elara chatbot.
     */
    public String getResponse(String input) {
        String response;

        if (input.equals("bye")) {
            ui.showExitMessage();
            response = ui.getLastResponse();
            return response;
        }

        try {
            Command cmd = Parser.parse(input);
            cmd.execute(taskList, ui, storage);
            response = ui.getLastResponse();
        } catch (InvalidInputException e) {
            ui.showInvalidCommandMessage(e);
            response = ui.getLastResponse();
        }
        return response;
    }

    /**
     * Gets welcome message from Elara chatbot
     *
     * @return welcome message string.
     */
    public String welcomeUser() {
        ui.showWelcomeMessage();
        return ui.getLastResponse();
    }

    /**
     * The main method that starts the Elara chatbot program.
     * Initializes the chatbot with the given file path to store tasks.
     *
     * @param args Command-line arguments (not used in this program).
     */
    public static void main(String[] args) {
        new Elara(FILE_PATH).run();
    }
}
