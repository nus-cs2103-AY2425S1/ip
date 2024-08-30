package bimo.command;

import bimo.Storage;
import bimo.TaskList;
import bimo.Ui;

/**
 * Creates an object that can execute user input.
 */
public abstract class Command {
    private boolean isQuit = false;

    /**
     * Sets command to exit bot.
     */
    public void quitBot() {
        this.isQuit = true;
    }

    /**
     * Retrieves boolean value of bot status.
     *
     * @return Status on whether to exit chatbot.
     */
    public boolean getIsQuit() {
        return this.isQuit;
    }

    /**
     * Decides what action to take.
     *
     * @param tasks List of user tasks.
     * @param ui User interface that interacts with users.
     * @param storage Storage that writes and load files.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
}