package Johnson.command;

public abstract class IndexedCommand extends Command {

    protected static final String INVALID_INDEX_MSG = "Hit your head, Chief? Gotta give me an index above 0!";

    protected static final String INDEX_OUT_OF_BOUNDS_MSG = "You're reaching for the stars, Chief. That task doesn't exist!";

    protected int index;

    public IndexedCommand(int index) {
        this.index = index;
    }

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
