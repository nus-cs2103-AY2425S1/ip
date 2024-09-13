package chatsy.command;

import chatsy.TaskManager;

/**
 * Handles the "bye" command which exits the application.
 */
public class ByeCommand extends Command {

    @Override
    public String execute(TaskManager taskManager) {
        return "Goodbye!";
    }

    @Override
    public boolean shouldExit() {
        return true; // Signals the application to exit
    }
}

