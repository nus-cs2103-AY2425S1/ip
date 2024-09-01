package king.commands;

import king.KingException;
import king.Storage;
import king.TaskList;
import king.Ui;

public class MarkCommand extends Command {
    private int taskIndex;

    public MarkCommand(String taskIndex) {
        this.taskIndex = Integer.parseInt(taskIndex) - 1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws KingException {
        try {
            tasks.markTaskAsDone(taskIndex);
            ui.showTaskMarked(tasks.getTask(taskIndex));
            storage.save(tasks.getTaskList());
        } catch (IndexOutOfBoundsException e) {
            throw new KingException("Invalid list number entered!");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
