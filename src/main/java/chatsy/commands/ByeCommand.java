package chatsy.commands;

import chatsy.TaskManager;

/**
 * Represents a command that handles the "bye" action to exit the application.
 * This command is responsible for signaling the termination of the application.
 */
public class ByeCommand extends Command {

    /**
     * Executes the "bye" command by returning a farewell message to the user.
     * This method does not modify the state of the TaskManager.
     *
     * @param taskManager The TaskManager instance managing the tasks (not used in this command).
     * @return A farewell message indicating the application is closing.
     */
    @Override
    public String execute(TaskManager taskManager) {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Indicates whether this command should trigger the application to exit.
     *
     * @return {@code true} to exit the application.
     */
    @Override
    public boolean shouldExit() {
        return true;
    }
}
