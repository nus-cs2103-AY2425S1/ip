package bimo.command;

import bimo.Storage;
import bimo.TaskList;
import bimo.Ui;

public abstract class Command {
    private boolean isQuit = false;
    public void quitBot() {
        this.isQuit = true;
    }
    public boolean getIsQuit() {
        return this.isQuit;
    }
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
}