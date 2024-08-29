package colress.command;

import colress.TaskList;
import colress.Ui;

public final class DeleteCommand extends Command {
    public DeleteCommand() {
        super("Got it. I have removed the task from your list.");
    }
    @Override
    public void execute(Ui ui, TaskList taskList) {
        int taskNumber = ui.promptTaskNumber(taskList);
        if (taskNumber != -1) {
            taskList.deleteTask(taskNumber);
            ui.printConfirmationMessage(taskList, getExecuteSuccessMessage());
        }
    }
}
