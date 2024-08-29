package justbot.command;

import justbot.task.TaskList;
import justbot.task.Task;
import justbot.ui.Ui;
import justbot.storage.Storage;
import justbot.exception.JustbotException;

public class UnmarkCommand extends Command {
    private int unmarkNumber;

    public UnmarkCommand(int unmarkNumber) {
        this.unmarkNumber = unmarkNumber;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            taskList.validateUnmarkTaskNumber(unmarkNumber);
            Task currTask = taskList.get(this.unmarkNumber - 1);
            currTask.setIsDone(false);
            ui.unmarkMessage(taskList, unmarkNumber);
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
