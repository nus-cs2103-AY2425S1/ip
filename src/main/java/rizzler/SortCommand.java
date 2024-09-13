package rizzler;

/**
 * Represents the command to sort tasks within Rizzler's
 * TaskList.
 */
public class SortCommand implements Command {

    /**
     * Executes the command to sort tasks in Rizzler's TaskList.
     *
     * @param tasks TaskList supplied by Rizzler.
     * @param ui Ui supplied by Rizzler.
     * @param fileStorage FileStorage supplied by Rizzler.
     * @return String detailing the sorting.
     * @throws RizzlerException If the TaskList is empty.
     */
    public String execute(TaskList tasks,
                          Ui ui,
                          FileStorage fileStorage) throws RizzlerException {
        String output = tasks.sort();
        fileStorage.save(tasks.getListToSave());
        return output;
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
