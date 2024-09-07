package myapp.core;

import myapp.command.Command;
import myapp.task.TaskList;

/**
 * Represents the BingBongBot that handles user interactions, manages tasks, and
 * processes commands. The bot is capable of executing commands based on user input
 * and can determine when to exit the application.
 */
public class BingBongBot {

    /** Indicates whether the bot has received an exit command. */
    private boolean isExit = false;

    /** The storage system used to load and save tasks. */
    private final Storage storage;

    /** The list of tasks managed by the bot. */
    private TaskList tasks;

    /**
     * Constructs a BingBongBot with the specified storage.
     *
     * @param storage The storage system to load and save tasks.
     */
    public BingBongBot(Storage storage) {
        this.storage = storage;
        try {
            this.tasks = new TaskList(storage.load());
        } catch (BingBongException e) {
            this.tasks = new TaskList();
        }
    }

    /**
     * Processes the user's input, executes the corresponding command, and returns the bot's response.
     * This method also checks if the executed command is an exit command, and updates the exit status accordingly.
     *
     * @param input The user's input string.
     * @return The bot's response after executing the command.
     */
    public String getResponse(String input) {
        try {
            // Parse the input into a Command
            Command command = Parser.parseCommand(input);

            // Execute the command and capture the response
            String response = command.execute(tasks, storage);
            assert response != null : "response should not be null";

            // Save isExit status
            isExit = command.isExit();

            // Return response from the bot
            return response;

        } catch (BingBongException e) {
            return "Error: " + e.getMessage();
        } catch (NumberFormatException e) {
            return "Invalid task number. Please enter a valid number.";
        } catch (IndexOutOfBoundsException e) {
            return "Task number is out of range. Please enter a valid task number.";
        } catch (Exception e) {
            return "An unexpected error occurred: " + e.getMessage();
        }
    }

    /**
     * Returns the exit status of the bot.
     * This method checks whether the bot should terminate based on the last executed command.
     *
     * @return true if the last executed command was an exit command, false otherwise.
     */
    public boolean isExit() {
        return isExit;
    }
}
