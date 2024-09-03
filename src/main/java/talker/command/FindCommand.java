package talker.command;

import talker.Storage;
import talker.TalkerException;
import talker.Ui;
import talker.task.TaskList;

/**
 * Represents a find command to find certain keywords in task descriptions
 */
public class FindCommand extends Command {
    // string representing keyword
    private String[] parsed;

    public FindCommand(String[] parsed) {
        this.parsed = parsed;
    }

    /**
     * Executes find command
     *
     * @param list list of tasks to be searched through
     * @param ui ui object to print output
     * @param storage storage object to read/write file
     * @return String representing outcome of this event
     * @throws TalkerException if no tasks found
     */
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) throws TalkerException {
        return list.findTask(parsed[1], ui);
    }

}
