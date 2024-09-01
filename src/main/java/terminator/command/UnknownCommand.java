package terminator.command;

import java.util.ArrayList;

import terminator.task.Task;

/**
 * Concrete class representing an unknown command given by the user.
 */
public class UnknownCommand extends Command {

    public UnknownCommand() {
        super();
    }

    /**
     * Prints out an error message, prompting the user to enter another command.
     *
     * @param todoList The task list.
     */
    @Override
    public String execute(ArrayList<Task> todoList) {
        return "I do not understand your request. Clarify your command.";
    }
}
