package froggy;

/**
 * An executable command.
 */
public abstract class Command {
    /**
     * Executes given command based on command type.
     *
     * @param taskList Task list to execute command on.
     * @param ui Ui used to as output.
     * @param storage storage to save tasks.
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage);

    /**
     * Executes given command based on command type and returns output as String.
     *
     * @param taskList Task list to execute command on.
     * @param ui Ui used to as output.
     * @param storage storage to save tasks.
     * @return String output to user.
     */
    public abstract String executeAndGetOutput(TaskList taskList, Ui ui, Storage storage);

    /**
     * Returns true if command is an exit command.
     */
    public abstract boolean isExit();
}
