package susan.command;

import susan.task.Task;
import susan.task.TaskList;
import susan.ui.Storage;
import susan.ui.SusanException;
import susan.ui.Ui;

/**
 * Represents a command to mark or unmark tasks by user-input index.
 */
public class MarkCommand extends Command {
    private String[] commandParts;
    private boolean isMark;

    public MarkCommand(String[] commandParts, boolean isMark) {
        this.commandParts = commandParts;
        this.isMark = isMark;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws SusanException {
        try {
            int taskIndex = Integer.parseInt(commandParts[1]) - 1;
            Task task = tasks.get(taskIndex);
            if (isMark) {
                task.markAsDone();
            } else {
                task.undoMark();
            }
            storage.load(tasks);
            return ui.showMarkTask(task, tasks.size());
        } catch (Exception e) {
            throw new SusanException("Please enter a valid task index to mark / unmark.");
        }
    }
}