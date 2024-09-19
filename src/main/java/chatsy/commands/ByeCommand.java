package chatsy.commands;

import chatsy.TaskManager;

/**
 * Handles the "bye" command which exits the application.
 */
public class ByeCommand extends Command {

    @Override
    public String execute(TaskManager taskManager) {
        return "Bye. Hope to see you again soon!";
    }

    @Override
    public boolean shouldExit() {
        return true;
    }
}

