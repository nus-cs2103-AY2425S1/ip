package jbot;

import jbot.command.JBotCommand;
import jbot.util.Parser;
import jbot.util.Storage;

/**
 * Represents the core logic of the JBot application.
 * This class handles the initialization of necessary components and processes user input
 * through the chatbot's command interface.
 */
public class JBot {

    /**
     * Creates an instance of the JBot class and initializes the application components.
     */
    public JBot() {
        JBot.init();
    }

    /**
     * Initializes the application's backend logic by setting up storage, parsing utilities,
     * and loading existing data from storage.
     */
    private static void init() {
        Storage.init();
        Parser.init();
        Storage.parseData();
    }

    /**
     * Processes user input by interpreting the command and executing the corresponding action.
     * This method returns the result of the executed command.
     *
     * @param userInput The user's input as a string, containing the command and its arguments.
     * @return The response of the command execution as a string.
     */
    public String getResponse(String userInput) {
        try {
            JBotCommand command = Parser.parse(userInput);
            return command.run(userInput);
        } catch (Exception e) {
            e.printStackTrace();
            return "An error occurred while processing your command.";
        }
    }
}