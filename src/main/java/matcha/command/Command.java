package matcha.command;

import matcha.exception.MatchaException;

import matcha.storage.Storage;

import matcha.tasklist.TaskList;

import matcha.ui.Ui;

public abstract class Command {

    private boolean isExit = false;

    public void setExit(boolean exit) {
        isExit = exit;
    }

    public boolean isExit() {
        return isExit;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws MatchaException;

}
