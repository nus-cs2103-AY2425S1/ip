package talker.command;

import talker.Storage;
import talker.TalkerException;
import talker.Ui;
import talker.task.TaskList;

/**
 * Sets Priority of Task to certain level
 */
public class SetPriorityCommand extends Command {

    // String representing parsed command
    private String[] parsed;

    public SetPriorityCommand(String[] parsed) {
        this.parsed = parsed;
    }

    /**
     * Executes setPriority command
     *
     * @param list list task is to be deleted from
     * @param ui ui object to print output
     * @param storage storage object to read/write file
     * @return String representing outcome of this event
     * @throws TalkerException if unable to set priority of task
     */
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) throws TalkerException {
        return list.setPriorityOfTask(parsed, ui);
    }
}
