package command;

import task.TaskList;

/**
 * Command to be executed by Him.
 *
 * @author IsaacPangTH
 */
public abstract class Command {

    /**
     * Executes the command.
     *
     * @param list Task list stored by Him.
     * @return Message representing the execution of the command.
     */
    public abstract String execute(TaskList list);
}
