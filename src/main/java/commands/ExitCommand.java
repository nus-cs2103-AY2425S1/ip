package commands;

import io.Ui;
import task.TaskList;

/**
 * Represents a command to exit the application.
 * This command provides a farewell message indicating the application is closing.
 */
public class ExitCommand extends Command {

    @Override
    public String execute(TaskList taskList) {
        return Ui.getExitMessage();
    }
}
