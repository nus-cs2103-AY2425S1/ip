package chatgpt.command;

import chatgpt.exception.ChatBotException;
import chatgpt.storage.Storage;
import chatgpt.task.Task;
import chatgpt.task.TaskList;
import chatgpt.ui.Ui;

public class FindCommand extends Command {

    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }


    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage)
            throws ChatBotException {
        TaskList results = tasks.find(keyword);
        ui.showFound(results);
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
