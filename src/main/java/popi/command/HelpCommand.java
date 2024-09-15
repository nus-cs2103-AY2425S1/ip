package popi.command;

import popi.task.TaskList;
import popi.task.TaskManager;
import popi.ui.Ui;

/**
 * Represents a command to show the help message.
 */
public class HelpCommand extends Command {
    public HelpCommand() {
    }

    @Override
    public void execute(TaskList tasks, Ui ui, TaskManager taskManager) {
        ui.showHelp();
    }
}
