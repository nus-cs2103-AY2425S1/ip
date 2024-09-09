package chatgpt.command;

import chatgpt.exception.ChatBotException;

import chatgpt.task.TaskList;
import chatgpt.task.Task;

import chatgpt.ui.Ui;

import chatgpt.storage.Storage;

public class CompleteCommand extends Command {

    private boolean isCompleted;
    private int index;

    public CompleteCommand(int index, boolean isCompleted) {
        this.index = index;
        this.isCompleted = isCompleted;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage)
            throws ChatBotException {
        if (index > tasks.size()) {
            throw new ChatBotException("\tNo task exists for that index");
        }
        Task task = tasks.get(index-1);
        task.setCompleted(isCompleted);
        if (isCompleted) {
            ui.showCompleteTask(task);
        } else {
            ui.showUncompleteTask(task);
        }
        storage.save(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
