package ekud.commands;

import ekud.components.Storage;
import ekud.components.TaskList;
import ekud.task.Task;
import ekud.ui.Ui;

/**
 * Represents {@link Command} to list every {@link Task} in some {@link TaskList}.
 */
public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String responseListEmpty = "Would Ya look at that: No tasks to be found. Shocking ain't it";
        String responseAcknowledge = "Look at all these tasks:";
        String responseTaskFormat = "%d. %s";

        if (tasks.isEmpty()) {
            ui.addToBuffer(responseListEmpty);
            return;
        }

        ui.addToBuffer(responseAcknowledge);

        // display tasks
        int i = 1;
        for (Task task : tasks) {
            ui.addFormattedToBuffer(responseTaskFormat, i, task.toString());
            i++;
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
