package noosy.commands;

import noosy.exception.NoosyException;
import noosy.storage.Storage;
import noosy.task.Task;
import noosy.task.TaskList;
import noosy.ui.Ui;

/**
 * Represents the find command in the Noosy task management chatbot.
 * This command is responsible for finding all tasks with the relevant keyword.
 */
public class FindCommand extends Command {

    /** The keyword to search for in tasks. */
    private String keyword;

    /** The list of tasks that contain the keyword. */
    private TaskList tasksWithKeyword;

    /**
     * Constructs a new FindCommand with the specified keyword.
     *
     * @param keyword The keyword to search for in tasks.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
        this.tasksWithKeyword = new TaskList();
    }

    /**
     * Executes the find command.
     * This method searches for tasks containing the specified keyword,
     * and displays the results to the user via the UI.
     *
     * @param tasks   The current list of all tasks.
     * @param ui      The user interface for displaying results.
     * @param storage The storage for saving any changes (not used in this command).
     * @throws NoosyException If there's an error during the execution of the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NoosyException {
        this.tasksWithKeyword = tasks.find(this.keyword);
        ui.showFindCommand(tasksWithKeyword);
    }
}
