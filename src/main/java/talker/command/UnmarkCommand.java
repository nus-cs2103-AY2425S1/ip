package talker.command;

import talker.Storage;
import talker.TalkerException;
import talker.Ui;
import talker.task.TaskList;

public class UnmarkCommand extends Command {
    private String[] parsed;

    public UnmarkCommand(String[] parsed) {
        this.parsed = parsed;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws TalkerException {
        list.unmarkTaskComplete(parsed, ui);
    }
}
