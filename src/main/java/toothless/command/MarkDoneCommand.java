package toothless.command;

import toothless.exceptions.MissingIndexExceptions;
import toothless.exceptions.ToothlessExceptions;
import toothless.storage.Storage;
import toothless.task.TaskList;
import toothless.ui.Ui;

/**
 * Represents a command to mark a task as done.
 */
public class MarkDoneCommand extends Command {

    private String description;

    /**
     * Constructor for MarkDoneCommand
     *
     * @param description Description of the task to be marked as done.
     *                    Should be the index of the task.
     */
    public MarkDoneCommand(String description) {
        this.description = description;
    }

    @Override
    public String executeCommand(TaskList taskList, Ui ui, Storage storage) throws ToothlessExceptions {
        if (description.isEmpty()) {
            throw new MissingIndexExceptions("mark", "mark <index>");
        }
        int markIndex = Integer.parseInt(description);
        String response = taskList.markDone(markIndex, ui);
        storage.saveTask(taskList.getList());
        return response;
    }
}
