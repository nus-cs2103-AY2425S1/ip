package genji.command;

import genji.task.TaskList;
import genji.Ui;
import genji.Storage;

/**
 * An abstract class that deals with user's commands
 */
public abstract class Command {

    /**
     * Executes operations according to different commands
     * @param list Task list to be modified
     * @param ui Ui for display
     * @param s Storage for saving
     */
    public abstract void execute(TaskList list, Ui ui, Storage s);

    /**
     * Distinguishes if it is a bye command
     * @return Exit or not
     */
    public abstract boolean isExit();

    /**
     * Get response message for GUI
     * @return Formatted string
     */
    public abstract String getResponse();
}
