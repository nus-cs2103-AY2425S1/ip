package colress.command;

import colress.TaskList;
import colress.Ui;

public final class CheckCommand extends Command {
    public CheckCommand() {
        super("Sure. I have marked this task on your list as done:\n");
    }

    @Override
    public void execute(Ui ui, TaskList taskList) {
        int taskNumber = ui.promptTaskNumber(taskList);
        if (taskNumber != -1) {
            ui.printConfirmationMessage(taskList, getExecuteSuccessMessage() + taskList.checkTask(taskNumber));
        }
    }
}
