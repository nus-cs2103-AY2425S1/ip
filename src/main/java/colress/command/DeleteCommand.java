package colress.command;

import colress.TaskList;
import colress.Ui;

/**
 * Represents the delete command that removes a task from the list of tasks.
 */
public final class DeleteCommand extends Command {
    public DeleteCommand() {
        super("Got it. I have removed the task from your list.");
    }

    /**
     * Facilitates removing a task from the provided TaskList, using the provided Ui object to receive input
     * from the user regarding which task to remove.
     */
    @Override
    public void execute(Ui ui, TaskList taskList) {
        int taskNumber = ui.promptTaskNumber(taskList);
        if (taskNumber != -1) {
            taskList.deleteTask(taskNumber);
            ui.printConfirmationMessage(taskList, getExecuteSuccessMessage());
        }
    }
}
