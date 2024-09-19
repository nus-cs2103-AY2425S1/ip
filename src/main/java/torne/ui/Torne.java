package torne.ui;

import torne.command.Command;
import torne.command.Parser;
import torne.exception.TorneException;
import torne.storage.Storage;
import torne.task.*;

import java.util.*;

/**
 * This is the main class for `torne.ui.Torne`.
 * All the main commands are here.
 */

public class Torne {
    private final ChatOutput output;
    private final TaskHandler taskHandler;
    private final Storage storage;
    private final Parser parser;

    public Torne() {
        output = new ChatOutput();
        storage = new Storage();
        taskHandler = new TaskHandler(storage.loadTaskData());
        parser = new Parser(taskHandler, output, storage);
    }

    /**
     * Runs Torne by itself, via a CLI
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        String input;

        // greet user
        output.showGreeting();

        while (true) {
            // Read input from user
            input = scanner.nextLine().trim();

            // first check if it's an exit (`bye`) command
            if (input.equals("bye")) {
                output.showExitMessage();

                // save tasks
                storage.saveTasks(taskHandler.getTaskList());

                break;
            }

            // else, parse and handle the input :D
            try {
                Command c = parser.parse(input);
                // TODO doesn't do anything atm, the current method is that Parser executes with its args
                // would be better to set the args of the command from within parser
                // then you execute outside here?
            } catch (TorneException e) {
                output.error(e.toString());
            }

        }
    }

    /**
     * Takes in an input command, sends it to the Torne parser and returns the output.
     * To be used to communicate with Torne for GUI chatbot use.
     * <p></p>
     * Note: the parser does not handle "bye" and other exit commands.
     *
     * @param input String input command.
     * @return output of command.
     */
    public String getResponse(String input) {
        // currently how this works is that it gets parser to execute the command
        // then just takes the response from chatOutput
        try {
            Command c = parser.parse(input);
            // same issue as above

            return output.getCurrentOut();
        } catch (TorneException e) {
            output.error(e.toString());
            return output.getCurrentOut();
        }
    }

    /**
     * Gets the instance of Torne to initialise and show the start message, then returns the start message.
     *
     * @return start message.
     */
    public String getStartMessage() {
        output.showGreeting();
        return output.getCurrentOut();
    }

    // =================== MAIN ===================================
    public static void main(String[] args) {
        new Torne().run();
    }
}
