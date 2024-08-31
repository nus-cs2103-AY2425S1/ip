package luna.command;

import luna.Storage;
import luna.TaskList;

/**
 * Represents a command to end the chatbot session.
 */
public class ExitCommand extends Command {

    @Override
    public String execute(TaskList tasks, Storage storage) {
        System.out.println("Bye! Hope to see you again soon!");
        return null;
    }
}
