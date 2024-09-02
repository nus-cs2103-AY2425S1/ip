package alisa.command;

import alisa.AlisaException;
import alisa.Storage;
import alisa.task.TaskList;
import alisa.Ui;

public abstract class Command {

    /**
     * Performs an operation based on the given command.
     *
     * @param taskList List of all the tasks.
     * @param ui Ui that displays messages and interacts with user.
     * @param storage Storage that saves data into a file.
     * @throws AlisaException If the operation fails.
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws AlisaException;

    /**
     * Indicates whether to exit the program or not.
     *
     * @return true if it is an exit command, false otherwise.
     */
    public abstract boolean isExit();

}
