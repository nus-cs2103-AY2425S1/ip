package ekud.commands;

import ekud.components.Storage;
import ekud.components.Ui;
import ekud.task.Task;
import ekud.components.TaskList;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.isEmpty()) {
            ui.printOutput("Would Ya look at that: No tasks to be found. Shocking ain't it");
        } else {
            ui.printOutput("Look at all these tasks:");
            int i = 1;
            for (Task task : tasks) {
                String line = String.format("%d. %s", i, task);
                ui.printOutput(line);
                i++;
            }
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
