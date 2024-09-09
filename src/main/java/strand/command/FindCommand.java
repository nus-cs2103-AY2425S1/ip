package strand.command;

import strand.Storage;
import strand.TaskList;
import strand.Ui;

/**
 * The {@code FindCommand} class represents a command to find all the tasks in the list
 * which contain the word or phrase
 */
public class FindCommand extends Command {
    private final String segment;

    public FindCommand(String segment) {
        this.segment = segment;
    }
    /**
     * Executes the command with the given task list, UI, and storage.
     *
     * @param tasks   The {@code TaskList} containing the tasks to be operated on.
     * @param ui      The {@code Ui} object used for interacting with the user.
     * @param storage The {@code Storage} object used for saving task data.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.listTasks(tasks.getFoundTasks(segment));
    }
}
