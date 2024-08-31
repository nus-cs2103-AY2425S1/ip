package orion.commands;

import orion.chatbot.Storage;
import orion.chatbot.TaskList;
import orion.chatbot.Ui;

import orion.exceptions.OrionException;

public abstract class Command {

    boolean isExit;

    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    public abstract void execute(TaskList tasks, Storage storage, Ui ui) throws OrionException;

    public boolean isExit() {
        return this.isExit;
    }
}
