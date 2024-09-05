package torne.ui;

import torne.command.Command;
import torne.command.Parser;
import torne.exception.TorneException;
import torne.exception.TorneInvalidCommandException;
import torne.storage.Storage;
import torne.task.*;

import java.util.*;

/**
 * This is the main class for `torne.ui.Torne`.
 * All the main commands are here.
 */

public class Torne {
    private ChatOutput output;
    private TaskHandler taskHandler;
    private Storage storage;
    private Parser parser;
    private static final String[] NO_ARGS = new String[0];
    private static final String[] DEFAULT_ARG = {""};
    private static final Map<String, String[]> COMMANDS = Map.of(
            "bye", NO_ARGS,
            "list", NO_ARGS,
            "help", NO_ARGS,
            "mark",DEFAULT_ARG,
            "unmark", DEFAULT_ARG,
            "todo", NO_ARGS,
            "deadline", new String[]{"", "by"},
            "event", new String[]{"", "from", "to"},
            "delete", DEFAULT_ARG
    );

    public Torne() {
        output = new ChatOutput();
        storage = new Storage();
        taskHandler = new TaskHandler(storage.loadTaskData());
        parser = new Parser(taskHandler, output, storage);
    }

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


    // =================== MAIN ===================================
    public static void main(String[] args) {
        new Torne().run();
    }
}
