package colress.command;

import colress.TaskList;
import colress.Ui;

/**
 * Represents the check command that marks a task in the list of tasks as done.
 */
public final class CheckCommand extends Command {
    public CheckCommand() {
        super("Sure. I have marked this task on your list as done:\n");
    }

    /**
     * Facilitates marking a task in the provided TaskList as done, using the provided Ui object to receive input from
     * the user regarding which task to mark.
     */
    @Override
    public void execute(Ui ui, TaskList taskList) {
        int taskNumber = ui.promptTaskNumber(taskList);
        if (taskNumber != -1) {
            ui.printConfirmationMessage(taskList, getExecuteSuccessMessage() + taskList.checkTask(taskNumber));
        }
    }
}
