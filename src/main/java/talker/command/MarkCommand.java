package talker.command;

import talker.Storage;
import talker.TalkerException;
import talker.Ui;
import talker.task.TaskList;

/**
 * Represents mark command to mark task as complete
 */
public class MarkCommand extends Command {
    // string representing task number to be marked
    private String[] parsed;

    public MarkCommand(String[] parsed) {
        this.parsed = parsed;
    }

    /**
     * Exectues mark command
     *
     * @param list list of tasks where target task is located in
     * @param ui ui object to print output
     * @param storage storage object to read/write file
     * @throws TalkerException if unable to mark task as complete
     */
    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws TalkerException {
        list.markTaskComplete(parsed, ui);
    }
}
