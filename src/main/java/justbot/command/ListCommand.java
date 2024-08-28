package justbot.command;

import justbot.task.Task;
import justbot.task.TaskList;
import justbot.ui.Ui;
import justbot.storage.Storage;
import justbot.exception.JustbotException;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            taskList.validateNotEmpty();
            ui.listMessage(taskList);
        } catch (JustbotException e) {
            ui.getJustBotExceptionMessage(e);
        }
    }

    @Override
    public Task getTask() {
        return null;
    }
}
