package Commands;

import Exceptions.BrockException;
import Storage.Storage;
import Tasks.TaskList;
import Ui.Ui;
import Utility.Utility;

public class UnmarkCommand extends Command {
    public UnmarkCommand(String command) {
        super(command);
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks) throws BrockException {
        String command = super.getCommand();
        Utility.validateStatus(command, Utility.Action.UNMARK, tasks);

        int targetIndex = Utility.getTargetIndex(command);
        tasks.unmarkTask(targetIndex);
        ui.displayResponse("OK, I've marked this task as not done yet:\n"
                + "  "
                + tasks.getTaskDetails(targetIndex));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
