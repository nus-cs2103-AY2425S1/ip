package kai.commands;

import kai.Ui;
import kai.tasks.Task;

/**
 * This Command marks the Task given as incomplete
 * Can be modified as one-use later on if needed?
 */
public class UnmarkCommand extends Command {
    private final Task task;

    /**
     * Constructs UnmarkCommand with the relevant Task
     * it needs to be applied to
     *
     * @param task the Task that needs to be marked incomplete
     */
    public UnmarkCommand(Task task) {
        this.task = task;
    }

    @Override
    public void invoke(Ui ui) {
        task.setIncomplete();
        ui.showUnmarkCommandResults(task);
    }
}
