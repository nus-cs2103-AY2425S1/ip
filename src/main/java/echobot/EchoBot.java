package echobot;
import echobot.task.TaskList;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

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
        // Load tasks when starting
        Storage.loadTasksFromFile(tasks);

        // Initialize parser and UI
        Parser parser = new Parser(tasks);
        Ui ui = new Ui();

        // Start the user interface
        ui.start();

        // Process user commands
        while (true) {
            String userInput = ui.nextInput();
            Command command = parser.parse(userInput);
            command.run();
            if (command.isExit()) {
                break;
            }
        }

        // Exit the user interface
        ui.exit();
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        Parser parser = new Parser(tasks);
        Command command = parser.parse(input);

        // Create a ByteArrayOutputStream to capture the output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);

        // Save the current System.out
        PrintStream originalOut = System.out;

        try {
            // Redirect System.out to the printStream
            System.setOut(printStream);

            // Execute the command which will print to the redirected output
            command.run();

            // Flush the printStream to capture the output into outputStream
            printStream.flush();

            // Convert the captured output to a string and return it
            return outputStream.toString().trim();
        } finally {
            // Restore the original System.out
            System.setOut(originalOut);
        }
    }
}
