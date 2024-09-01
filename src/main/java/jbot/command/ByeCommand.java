package jbot.command;

import jbot.JBot;

/**
 * Represents a command that terminates the application.
 * This command prints a farewell message and then closes the application.
 * Implements {@link JBotCommand} to adhere to the command interface.
 */
public class ByeCommand implements JBotCommand {

    private static final ByeCommand instance = new ByeCommand();

    private ByeCommand() {
    }

    /**
     * Returns the singleton instance of {@link ByeCommand}.
     *
     * @return The singleton instance of {@link ByeCommand}.
     */
    public static ByeCommand getInstance() {
        return ByeCommand.instance;
    }

    /**
     * Executes the Bye command using the provided input string.
     * Prints a farewell message and invokes {@link JBot#close()} to terminate the application.
     *
     * @param input The user input string. This parameter is not used in this implementation.
     */
    @Override
    public void run(String input) {
        System.out.println("Bye. Hope to see you again soon!");
        JBot.close();
    }
}