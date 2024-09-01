package mummy.command;

import mummy.task.TaskList;
import mummy.ui.Ui;
import mummy.utility.Storage;

/**
 * Represents a command to exit the program.
 * When executed, it displays a farewell message to the user.
 * This command is used to terminate the program.
 */
public class ByeCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.show("Bye. Hope to see you again soon!\n");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
