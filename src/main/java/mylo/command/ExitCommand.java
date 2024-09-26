package mylo.command;

import mylo.task.TaskList;
import mylo.ui.Tui;

/**
 * Represents a command to terminate the application.
 * <p></p>
 * <p>The {@code ExitCommand} class is responsible for triggering the exit sequence
 * by calling the exit method of the user interface.</p>
 *
 * @author cweijin
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command, triggering the application to terminate.
     * <p></p>
     * <p>This method calls the {@code exit} method on the {@code Tui} to
     * terminate the application.</p>
     *
     * @param list The task list (unused in this command).
     * @param tui   The user interface that handles the exit process.
     */
    @Override
    public String execute(TaskList list, Tui tui) {
        return "Goodbye. Have a nice day ahead!";
    }
}
