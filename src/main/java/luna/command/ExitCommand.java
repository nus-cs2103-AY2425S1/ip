package luna.command;

import luna.Storage;
import luna.TaskList;

/**
 * Represents a command to end the chatbot session.
 */
public class ExitCommand extends Command {

    @Override
    public String execute(TaskList tasks, Storage storage) {
        return "Bye! Hope to see you again soon!";
    }

    @Override
    public String undo(TaskList tasks, Storage storage) {
        return "Nothing to undo for 'exit' command";
    }

    @Override
    public Command getPreviousCommand() {
        return null;
    }
}
