package michaelscott.command;

import michaelscott.task.TaskList;

/**
 * Represents a command to exit the application.
 * This command implements the Command interface.
 */
public class ExitCommand implements Command {

    @Override
    public String execute(TaskList tasks) {
        return "Catch you on the flipity flip!";
    }

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public String getSimpleName() {
        return "ExitCommand";
    }
}
