package chatgpt.command;

import chatgpt.exception.ChatBotException;

import chatgpt.task.TaskList;
import chatgpt.task.Task;

import chatgpt.ui.Ui;

import chatgpt.storage.Storage;

public class DeleteCommand extends Command {

    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage)
            throws ChatBotException {
        if (index > tasks.size()) {
            throw new ChatBotException("\tNo task exists for that index");
        }

        Task removedTask = tasks.get(index-1);
        tasks.remove(index-1);
        ui.showDeleteTask(removedTask, tasks.size());
        storage.save(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
