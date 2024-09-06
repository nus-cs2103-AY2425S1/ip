package elara.main;

import elara.command.Command;
import elara.command.ExitCommand;
import elara.parser.Parser;
import elara.storage.Storage;
import elara.task.InvalidInputException;
import elara.task.TaskList;
import elara.ui.Ui;

/**
 * The main class for the Elara chatbot application.
 * This class manages the overall programe flow, handling user input,
 * executing commands, and interacting with the task list and storage.
 */
public class Elara {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

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
     * The main method that starts the Elara chatbot program.
     * Initializes the chatbot wiht the given file path to store tasks.
     *
     * @param args Command-line arguments (not used in this program).
     */
    public static void main(String[] args) {
        new Elara("./data/Elara.txt").run();
    }
}