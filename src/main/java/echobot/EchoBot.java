package echobot;
import echobot.task.*;

/**
 * Main class for the EchoBot application.
 * Initializes the bot, loads tasks, and starts the user interface.
 */
public class EchoBot {
    private static final TaskList tasks = new TaskList();

    /**
     * The entry point for the EchoBot application.
     * Loads tasks from the file, initializes the parser and user interface,
     * and processes user commands until an exit command is received.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        Storage.loadTasksFromFile(tasks); // Load tasks when starting
        Parser parser = new Parser(tasks); // Initialize parser
        Ui ui = new Ui();

        ui.start();
        while (true) {
            String userInput = ui.nextInput();
            Command command = parser.parse(userInput);
            command.run();
            if (command.isExit()) {
                break;
            }
        }
        ui.exit();
    }
}
