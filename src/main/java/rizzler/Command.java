package rizzler;

/**
 * The interface by which commands are handled in Rizzler.
 */
interface Command {

    /**
     * Executes the given command using the inputs supplied by Rizzler.
     *
     * @param tasks TaskList supplied by Rizzler.
     * @param ui Ui supplied by Rizzler.
     * @param fileStorage FileStorage supplied by Rizzler.
     * @return String response processed by Rizzler.
     * @throws RizzlerException If something goes wrong with command execution.
     */
    public String execute(TaskList tasks,
                        Ui ui,
                        FileStorage fileStorage) throws RizzlerException;

    /**
     * Checks if the command is to exit Rizzler.
     *
     * @return <code>true</code> if command is to exit Rizzler.
     */
    public boolean isExit();
}
