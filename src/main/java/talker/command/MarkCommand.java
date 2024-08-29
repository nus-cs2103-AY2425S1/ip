package talker.command;

import talker.Storage;
import talker.TalkerException;
import talker.Ui;
import talker.task.TaskList;

public class MarkCommand extends Command {
    private String[] parsed;

    public MarkCommand(String[] parsed) {
        this.parsed = parsed;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws TalkerException {
        list.markTaskComplete(parsed, ui);
    }
}
