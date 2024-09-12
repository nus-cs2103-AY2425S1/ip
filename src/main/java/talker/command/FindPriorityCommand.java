package talker.command;

import talker.Storage;
import talker.TalkerException;
import talker.Ui;
import talker.task.TaskList;

/**
 * Represents command to find all tasks in list of a certain priority level
 */
public class FindPriorityCommand extends Command {

    private String[] parsed;

    public FindPriorityCommand(String[] parsed) {
        this.parsed = parsed;
    }

    /**
     * Executes FindPriority command
     *
     * @param list list of tasks to be searched through
     * @param ui ui object to print output
     * @param storage storage object to read/write file
     * @return String representing outcome of this event
     * @throws TalkerException if no tasks found
     */
    public String execute(TaskList list, Ui ui, Storage storage) throws TalkerException {
        return list.findPriorityTask(parsed[1], ui);
    }
}
