package talker.command;

import talker.Storage;
import talker.TalkerException;
import talker.Ui;
import talker.task.TaskList;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws TalkerException {
        list.listTasks(ui);
    }

}
