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
    public void execute(ArrayList<Task> todoList) {
        System.out.println("I do not understand your request. Clarify your command.");
    }
}
