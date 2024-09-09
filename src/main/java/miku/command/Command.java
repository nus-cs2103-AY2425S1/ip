package miku.command;

import miku.utility.Response;
import miku.utility.Storage;
import miku.utility.TaskList;

/**
 * The abstract parent class of all other specfic command classes.
 */
public abstract class Command {

    /**
     * A abstract method to override: Execute the command.
     *
     * @param taskList The task list that takes in the new task.
     * @param ui       The UI to perform printing.
     * @param storage  The storage function to store the data into a text file.
     */
    public abstract void execute(TaskList taskList, Response ui, Storage storage);
}
