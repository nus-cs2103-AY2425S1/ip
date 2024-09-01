package jbot;

import jbot.command.ByeCommand;
import jbot.command.JBotCommand;
import jbot.util.Parser;
import jbot.util.Storage;
import jbot.util.Ui;

/**
 * The main entry point for the JBot application. This class initializes
 * the necessary components, handles user interactions, and controls the
 * application's lifecycle.
 */
public class JBot {
    private static boolean isRunning = true;

    /**
     * Initializes the application by setting up the storage, parser, and
     * loading existing data.
     */
    private static void init() {
        Storage.init();
        Parser.init();
        Storage.parseData();
    }

    /**
     * Closes the application by stopping the main loop and closing the user
     * interface.
     */
    public static void close() {
        JBot.isRunning = false;
        Ui.close();
    }

    /**
     * Starts the JBot application. Initializes the components, greets the user,
     * and enters the main loop to read and process user input until the
     * application is closed.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        JBot.init();
        Ui.greetUser();
        while (JBot.isRunning) {
            try {
                String userInput = Ui.readInput();
                JBotCommand command = Parser.parse(userInput);
                Ui.display(command, userInput);
                Storage.updateData();

            } catch (Exception e) {
                Ui.handleError(e);
            }
        }
    }
}