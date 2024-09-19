package Johnson.command;

/**
 * Represents an abstract command that takes an index as a parameter.
 * This class serves as a base for all indexed command implementations.
 */
public abstract class IndexedCommand extends Command {
    /**
     * The message that is displayed when an invalid index is given.
     */
    protected static final String INVALID_INDEX_MSG = "Hit your head, Chief? Gotta give me an index above 0!";

    /**
     * The message that is displayed when an index that is out of bounds is given.
     */
    protected static final String INDEX_OUT_OF_BOUNDS_MSG = "You're reaching for the stars, Chief. That task doesn't exist!";

    protected int index;

    public IndexedCommand(int index) {
        this.index = index;
    }

    /**
     * Checks if the index is valid.
     *
     * @return the error message if the index is invalid, null otherwise.
     */
    public String checkIndex() {
        if (index <= 0) {
            return INVALID_INDEX_MSG;
        }
        if (index > Command.taskList.taskCount()) {
            return INDEX_OUT_OF_BOUNDS_MSG;
        }
        return null;
    }

    public abstract String executeCommand();

}
