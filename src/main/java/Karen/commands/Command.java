package Karen.commands;

import Karen.tasks.TaskList;
import Karen.util.Ui;

public abstract class Command {

    /**
     * Executes this {@code Command}
     * @param taskList TaskList to execute command on
     * @param ui Ui object to handle user input/output
     */
    public abstract void execute(TaskList taskList, Ui ui);

    /**
     * Checks to see if this {@code Command} is of type {@code ExitCommand}
     * @return boolean
     */
    public abstract boolean isExit();
}
