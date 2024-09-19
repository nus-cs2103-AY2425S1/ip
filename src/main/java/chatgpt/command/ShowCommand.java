package chatgpt.command;

import chatgpt.exception.ChatBotException;
import chatgpt.storage.Storage;
import chatgpt.task.Task;
import chatgpt.task.TaskList;
import chatgpt.ui.Ui;

public class ShowCommand extends Command {
    private int index;

    public ShowCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage)
            throws ChatBotException {
        Task t = getTask(tasks);
        return ui.showTask(t);
    }

    private Task getTask(TaskList tasks) throws ChatBotException {
        if (index > tasks.size()) {
            throw new ChatBotException("\tNo task exists for that index");
        }
        return tasks.get(index - 1);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
