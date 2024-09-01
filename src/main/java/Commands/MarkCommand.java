package Commands;

import Exceptions.BrockException;
import Storage.Storage;
import Tasks.TaskList;
import Ui.Ui;
import Utility.Utility;

public class MarkCommand extends Command {
    public MarkCommand(String command) {
        super(command);
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks) throws BrockException {
        String command = super.getCommand();
        Utility.validateStatus(command, Utility.Action.MARK, tasks);

        int targetIndex = Utility.getTargetIndex(command);
        tasks.markTask(targetIndex);
        ui.displayResponse("Nice! I've marked this task as done:\n"
                + "  "
                + tasks.getTaskDetails(targetIndex));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
