package jude.command;
import jude.JudeException;
import jude.Storage;
import jude.TaskList;

/**
 * Represents a command where it sorts the current tasklist according to the tasks priority when executed.
 */
public class SortCommand extends Command {

    /**
     * Sorts the current tasklist according to a task priority, then also rewrites the savefile
     * in the order of changed tasklist.
     * @param list Tasklist.
     * @param storage Save file handler.
     * @throws JudeException
     */
    @Override
    public void execute(TaskList list, Storage storage) throws JudeException {
        list.sortByDates();
        setMessage("The list is sorted by deadlines. ");
        storage.save(list);
    }

    @Override
    public String getType() {
        return "SortCommand";
    }
}
