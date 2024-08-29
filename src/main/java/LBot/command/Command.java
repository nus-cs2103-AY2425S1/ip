package LBot.command;

import LBot.exception.FileException;
import LBot.helper.Ui;
import LBot.helper.Storage;
import LBot.helper.TaskList;

import LBot.exception.ExecuteCommandException;

/**
 * This abstract class contains the execute abstract method
 * used to carry out instructions for the respective commands.
 */
public abstract class Command {

    /**
     * Executes relevant methods to create/modify/delete tasks
     *
     * @param ui      handles user input and printing.
     * @param storage handles reading and writing to text file.
     * @param tasks   contains list of tasks.
     * @throws ExecuteCommandException thrown if method is unable to create the task.
     * @throws FileException           thrown if there is an error encountered while reading or writing to file.
     */
    public abstract void execute(Ui ui, Storage storage, TaskList tasks) throws ExecuteCommandException, FileException;
}
