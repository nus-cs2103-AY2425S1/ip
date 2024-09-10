package talker.command;

import talker.Storage;
import talker.TalkerException;
import talker.Ui;
import talker.task.TaskList;

/**
 * Represents unmark command to mark task as incomplete
 */
public class UnmarkCommand extends Command {
    // string representing target task number to be unmarked
    private String[] parsed;

    public UnmarkCommand(String[] parsed) {
        this.parsed = parsed;
    }

    /**
     * Executes unmark command
     *
     * @param list list of tasks where target task is located in
     * @param ui ui object to print output
     * @param storage storage object to read/write file
     * @return String representing outcome of this event
     * @throws TalkerException if unable to unmark task
     */
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) throws TalkerException {
        return list.unmarkTaskComplete(parsed, ui);
    }
}
