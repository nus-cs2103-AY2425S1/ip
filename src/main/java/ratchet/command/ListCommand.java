package ratchet.command;

import ratchet.storage.Storage;
import ratchet.task.TaskList;
import ratchet.ui.Ui;

/**
 * Command to list the task list.
 */
public class ListCommand extends Command {
    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui) {
        if (tasks.isEmpty()) {
            return ui.printWithSeparator("You have no tasks currently!");
        } else {
            StringBuilder s = new StringBuilder();
            for (int i = 0; i < tasks.size(); i++) {
                s.append(ui.printWithSeparator((i + 1) + "." + tasks.get(i)));
            }
            return s.toString();
        }
    }
}
