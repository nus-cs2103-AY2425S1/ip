package com.chatbaby;

public abstract class Command {
    String commandBody;

    public Command(String commandBody) {
        this.commandBody = commandBody;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws ChatBabyException;

    public boolean isExit() {
        return false;
    }
}
