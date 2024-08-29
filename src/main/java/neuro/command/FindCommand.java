package neuro.command;

import neuro.Storage;
import neuro.Ui;
import neuro.task.TaskList;

public class FindCommand extends Command {
    private final String searchQuery;

    public FindCommand(String searchQuery) {
        this.searchQuery = searchQuery;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.isEmpty()) {
            ui.showMessage("You currently have no tasks.");
        } else {
            ui.showMessage("Here are the matching tasks in your list:");
            int j = 1;
            for (int i = 0; i < tasks.getSize(); i++) {
                if (tasks.getTask(i).toString().contains(searchQuery)) {
                    ui.showMessage(j + ". " + tasks.getTask(i));
                    j++;
                }
            }
        }
    }
}
