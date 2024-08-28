package ekud.commands;

import ekud.exceptions.EkudException;
import ekud.components.Storage;
import ekud.components.Ui;
import ekud.task.Task;
import ekud.components.TaskList;

public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int index) {
        this.index = index - 1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EkudException {
        Task task = tasks.getTask(index);
        String previousSaveState = task.getSaveTaskString();
        task.markAsDone();
        String listStatus = tasks.isAllComplete()
                ? String.format("WOOHOO!! YOU DID IT! Everything is complete!! All %d of them",
                tasks.getCount())
                : String.format("Woohoo!! Only %d out of %d tasks more to go",
                tasks.getIncompleteCount(),
                tasks.getCount());
        String message = String.format("""
                        Wowie!! You've completed your task!
                        I shall mark it as complete in celebration!
                          %s
                        %s!""",
                task,
                listStatus);
        ui.printOutput(message);

        // update data file
        storage.updateTaskState(task, previousSaveState, ui);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
