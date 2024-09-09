package jbot.command;

/**
 * Represents a command in the JBot application.
 * Each command implements this interface and provides the logic for executing the command based on user input.
 */
public interface JBotCommand {

    /**
     * Executes the command using the provided input string.
     *
     * @param input The user input string containing the command and its arguments.
     */
    String run(String input);
}