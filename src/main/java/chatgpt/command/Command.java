package chatgpt.command;

import chatgpt.exception.ChatBotException;

import chatgpt.task.TaskList;

import chatgpt.ui.Ui;

import chatgpt.storage.Storage;

public abstract class Command {
    public abstract void execute(TaskList tasks,
                                 Ui ui, Storage storage) throws ChatBotException;
    public abstract boolean isExit();
}
