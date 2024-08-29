package talker.command;

import talker.Storage;
import talker.TalkerException;
import talker.Ui;
import talker.task.TaskList;

public class ExitCommand extends Command {

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws TalkerException {
        storage.writeFile(list);
        ui.printGoodBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }

}
