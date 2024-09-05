package mylo.command;

import mylo.task.TaskList;
import mylo.ui.Ui;

/**
 * Represents a command to terminate the application.
 * <p></p>
 * <p>The {@code ExitCommand} class is responsible for triggering the exit sequence
 * by calling the exit method of the user interface.</p>
 *
 * @author cweijin
 */
public class ExitCommand extends Command{

    /**
     * Executes the exit command, triggering the application to terminate.
     * <p></p>
     * <p>This method calls the {@code exit} method on the {@code Ui} to
     * terminate the application.</p>
     *
     * @param list The task list (unused in this command).
     * @param ui   The user interface that handles the exit process.
     */
    @Override
    public void execute(TaskList list, Ui ui) {
        ui.exit();
    }
}
