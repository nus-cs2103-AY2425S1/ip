package command;

import java.util.List;

import storage.Storage;
import task.Deadline;
import task.TaskList;
import ui.Ui;
import ui.UiGui;

/**
 * Represents a command that sorts the deadlines in the task list.
 */
public class SortDeadlineCommand extends Command {
    /**
     * Executes the command with the specified task list, CLI, and storage to display a chronologically
     * sorted list of deadlines to the user on the CLI.
     *
     * @param tasks The task list to be modified by the command.
     * @param ui The user interface to display messages to the user.
     * @param storage The storage to save the task list to file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        List<Deadline> sortedDeadlines = tasks.sortDeadlines();
        ui.showSortedDeadlines(sortedDeadlines);
    }

    /**
     * Executes the command with the specified task list, GUI, and storage to display a chronologically
     * sorted list of deadlines to the user on the GUI.
     *
     * @param tasks The task list to be modified by the command.
     * @param gui The GUI to display messages to the user.
     * @param storage The storage to save the task list to file.
     * @return The result of the command execution.
     */
    @Override
    public String executeGui(TaskList tasks, UiGui gui, Storage storage) {
        List<Deadline> sortedDeadlines = tasks.sortDeadlines();
        return gui.showSortedDeadlines(sortedDeadlines);
    }
}
