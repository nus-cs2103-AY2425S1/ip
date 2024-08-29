package ratchet.command;

import ratchet.storage.Storage;
import ratchet.task.TaskList;
import ratchet.ui.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) {
        ui.printLine();
        if (tasks.isEmpty()) {
            ui.printWithIndent("You have no tasks currently!");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                ui.printWithIndent((i + 1) + "." + tasks.get(i));
            }
        }
        ui.printLine();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
