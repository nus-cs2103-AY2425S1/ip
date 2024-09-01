package terminator.command;

import java.util.ArrayList;

import terminator.task.Task;

/**
 * Concrete class representing an empty line entered by the user.
 */
public class EmptyCommand extends Command {

    public EmptyCommand() {
        super();
    }

    /**
     * Prints an error message to the console.
     *
     * @param todoList The task list.
     */
    @Override
    public String execute(ArrayList<Task> todoList) {
        return "Insufficient data. Clarify your command.";
    }
}
