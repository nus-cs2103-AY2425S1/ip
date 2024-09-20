package Majima.command;

import Majima.MajimaException;
import Majima.storage.Storage;
import Majima.task.TaskList;
import Majima.ui.Ui;

/**
 * A skeleton of a class that represents a Command. Further changes to bot
 * behaviour can be passed as commands and added as new classes which extend from here.
 */
public abstract class Command {

    /**
     * Executes the relevant command, in this case adding the task to the list,
     * printing out the relevant text to the user and saves the new task into
     * the .txt file.
     *
     * @param tasks The task list to add this task to.
     * @param storage The text file to save this task to.
     * @param ui The console to give the notification of the generated task to.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws MajimaException;

    /**
     * Common identifier between all Command classes to know
     * whether to terminate. the main loop or not
     *
     * @return a boolean True or False.
     */
    public abstract boolean isExit();
}
