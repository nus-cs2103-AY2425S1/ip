package colress.command;

import colress.TaskList;
import colress.Ui;

/**
 * Represents the check command that marks a task in the list of tasks as not done.
 */
public final class UncheckCommand extends Command {
    public UncheckCommand() {
        super("Right. I have marked this task on your list as not done:\n");
    }

    /**
     * Facilitates marking a task in the provided TaskList as not done, using the provided Ui object to receive input
     * from the user regarding which task to mark.
     */
    @Override
    public void execute(Ui ui, TaskList taskList) {
        int taskNumber = ui.promptTaskNumber(taskList);
        if (taskNumber != -1) {
            ui.printConfirmationMessage(taskList, getExecuteSuccessMessage() + taskList.uncheckTask(taskNumber));
        }
    }
}
