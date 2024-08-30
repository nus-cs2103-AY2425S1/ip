package justbot.command;

import justbot.exception.JustbotException;
import justbot.storage.Storage;
import justbot.task.Task;
import justbot.task.TaskList;
import justbot.ui.Ui;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
            ui.findMessage(taskList, keyword);
    }

    @Override
    public Task getTask() {
        return null;
    }
}
