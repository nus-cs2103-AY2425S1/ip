package ratchet.command;

import ratchet.storage.Storage;
import ratchet.task.TaskList;
import ratchet.ui.Ui;

/**
 * Command to find task with keyword from task list.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructor for FindCommand.
     *
     * @param keyword Keyword of tasks to find.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) {
        TaskList filtered = tasks.filter(keyword);
        ui.printLine();
        if (filtered.isEmpty()) {
            ui.printWithIndent("There are no matching tasks in your list!");
        } else {
            ui.printWithIndent("Here are the matching tasks in your list:");
            for (int i = 0; i < filtered.size(); i++) {
                ui.printWithIndent((i + 1) + "." + filtered.get(i));
            }
        }
        ui.printLine();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
