package chatsy.commands;

import chatsy.TaskManager;

/**
 * Represents an unknown command issued by the user.
 */
public class UnknownCommand extends Command {

    private final String userInput;
    private final String arguments;

    public UnknownCommand(String userInput, String arguments) {
        this.userInput = userInput;
        this.arguments = arguments;
    }

    /**
     * Executes the unknown command and returns an error message.
     *
     * @param taskManager The task manager (not used in this command).
     * @return The error message indicating the command is unknown.
     */
    @Override
    public String execute(TaskManager taskManager) {
        if (arguments.isEmpty()) {
            return "OOPS!!! I'm sorry, but I don't know what \"" + userInput + "\" means :-(";
        } else {
            return "OOPS!!! I'm sorry, but I don't know what \"" + userInput + " " + arguments + "\" means :-(";
        }
    }

    /**
     * Unknown commands do not cause the application to exit.
     *
     * @return {@code false}, because the application should not exit.
     */
    @Override
    public boolean shouldExit() {
        return false;
    }
}
