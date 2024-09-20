package chatsy.commands;

import chatsy.TaskManager;

/**
 * Represents an unknown command issued by the user.
 * Provides feedback indicating that the input command is not recognized.
 */
public class UnknownCommand extends Command {

    private final String userInput;
    private final String arguments;

    /**
     * Constructs an {@code UnknownCommand} with the unrecognized user input and arguments.
     *
     * @param userInput The unrecognized command input by the user.
     * @param arguments The arguments provided with the command.
     */
    public UnknownCommand(String userInput, String arguments) {
        this.userInput = userInput;
        this.arguments = arguments;
    }

    /**
     * Executes the unknown command and returns an error message indicating the command is not recognized.
     *
     * @param taskManager The task manager (not used in this command).
     * @return A string response indicating the command is unknown.
     */
    @Override
    public String execute(TaskManager taskManager) {
        if (arguments.isEmpty()) {
            return String.format("OOPS!!! I'm sorry, but I don't know what \"%s\" means :-(", userInput);
        } else {
            return String.format("OOPS!!! I'm sorry, but I don't know what \"%s %s\" means :-(", userInput, arguments);
        }
    }

    /**
     * Unknown commands do not cause the application to exit.
     *
     * @return {@code false}, as unknown commands should not exit the application.
     */
    @Override
    public boolean shouldExit() {
        return false;
    }
}
