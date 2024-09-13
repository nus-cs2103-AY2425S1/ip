package ekud.commands;

import ekud.components.Storage;
import ekud.components.TaskList;
import ekud.exceptions.EkudException;
import ekud.task.Task;
import ekud.ui.Ui;

/**
 * Represents {@link Command} to list every {@link Task} in some {@link TaskList}.
 */
public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EkudException {
        super.execute(tasks, ui, storage);

        if (tasks.isEmpty()) {
            ui.addToBuffer("Would Ya look at that: No tasks to be found. Shocking ain't it");
        } else {
            ui.addToBuffer("Look at all these tasks:");
            int i = 1;
            for (Task task : tasks) {
                String line = String.format("%d. %s", i, task);
                ui.addToBuffer(line);
                i++;
            }
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
