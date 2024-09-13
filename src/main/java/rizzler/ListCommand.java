package rizzler;

/**
 * Represents a command to list the tasks present
 * in Rizzler's tasklist.
 */
class ListCommand implements Command {

    /**
     * Executes the command to list the tasks in Rizzler.
     *
     * @param tasks TaskList supplied by Rizzler.
     * @param ui Ui supplied by Rizzler.
     * @param fileStorage FileStorage supplied by Rizzler.
     * @return String detailing list of tasks.
     */
    public String execute(TaskList tasks,
                        Ui ui,
                        FileStorage fileStorage) {
        return tasks.list();
    }

    /**
     * Checks if this command is to exit Rizzler.
     *
     * @return <code>false</code> as this command does not exit Rizzler.
     */
    public boolean isExit() {
        return false;
    }
}
