package rizzler;

/**
 * Represents a command to exit Rizzler.
 */
class ExitCommand implements Command {

    /**
     * Does nothing as the command does not need to execute any tasks.
     *
     * @param tasks TaskList supplied by Rizzler.
     * @param ui Ui supplied by Rizzler.
     * @param fileStorage FileStorage supplied by Rizzler.
     */
    public String execute(TaskList tasks,
                        Ui ui,
                        FileStorage fileStorage) {
        return ui.showGoodbye();
    }

    /**
     * Checks if this command is to exit Rizzler.
     *
     * @return <code>true</code> as this command intends to exit Rizzler.
     */
    public boolean isExit() {
        return true;
    }
}
