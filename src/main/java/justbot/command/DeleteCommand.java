package justbot.command;

import justbot.task.Task;
import justbot.task.TaskList;
import justbot.ui.Ui;
import justbot.storage.Storage;
import justbot.exception.JustbotException;

public class DeleteCommand extends Command {
    private int deleteNumber;

    public DeleteCommand(int deleteNumber) {
        this.deleteNumber = deleteNumber;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            taskList.validateDeleteTaskNumber(this.deleteNumber);
            ui.deleteTaskMessage(taskList, this.deleteNumber);
            taskList.delete(this.deleteNumber);
            storage.saveTasks(taskList);
        } catch (JustbotException e) {
            ui.getJustBotExceptionMessage(e);
        }
    }

    @Override
    public Task getTask() {
        return null;
    }
}
