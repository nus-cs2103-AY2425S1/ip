package toothless.command;

import toothless.storage.Storage;
import toothless.task.TaskList;
import toothless.exceptions.MissingIndexExceptions;
import toothless.exceptions.ToothlessExceptions;
import toothless.ui.Ui;

public class MarkDoneCommand extends Command {

    private String description;
    private final static String DIVIDER = "*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n";

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
    public void executeCommand(TaskList taskList, Ui ui, Storage storage) throws ToothlessExceptions {
        if (description.isEmpty()) {
            throw new MissingIndexExceptions("mark", "mark <index>");
        }
        int markIndex = Integer.parseInt(description);
        taskList.markDone(markIndex, ui);
        storage.saveTask(taskList.getList());
    }
}
