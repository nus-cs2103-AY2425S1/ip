package neuro.command;

import neuro.Storage;
import neuro.Ui;
import neuro.task.TaskList;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.isEmpty()) {
            ui.showMessage("You currently have no tasks.");
        } else {
            ui.showMessage("Here is a list of all your tasks:");
            for (int i = 0; i < tasks.getSize(); i++) {
                ui.showMessage((i + 1) + ". " + tasks.getTask(i));
            }
        }
    }
}
