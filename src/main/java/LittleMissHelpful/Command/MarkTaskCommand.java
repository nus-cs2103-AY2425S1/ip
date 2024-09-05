package LittleMissHelpful.Command;

import LittleMissHelpful.Exception.InvalidCommandException;
import LittleMissHelpful.Exception.InvalidTaskFormatException;
import LittleMissHelpful.Task.Task;
import LittleMissHelpful.TaskList;
import LittleMissHelpful.Ui;
import LittleMissHelpful.Storage;

public class MarkTaskCommand extends Command {
    private final int taskIndex;

    public MarkTaskCommand(int i) {
        this.taskIndex = i - 1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidCommandException {
        try {
            Task task = tasks.get(this.taskIndex);
            tasks.markTask(this.taskIndex);
            ui.showMarkedTask(task);
            storage.save(tasks.getTasks());
        } catch (InvalidTaskFormatException e) {
            throw new InvalidCommandException("Task number out of range. Please provide a valid task number.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
