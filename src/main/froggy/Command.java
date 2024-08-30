package main.froggy;

public abstract class Command {
    /**
     * Executes given command based on command type.
     * @param taskList Task list to execute command on
     * @param ui
     * @param storage
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage);

    /**
     * Returns if command is an exit command.
     */
    public abstract boolean isExit();
}
