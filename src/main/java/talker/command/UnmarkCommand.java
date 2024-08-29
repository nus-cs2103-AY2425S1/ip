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
     * @throws TalkerException if unable to unmark task
     */
    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws TalkerException {
        list.unmarkTaskComplete(parsed, ui);
    }
}
