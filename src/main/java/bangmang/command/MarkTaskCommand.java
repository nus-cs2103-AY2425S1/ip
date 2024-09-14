package LittleMissHelpful.Command;

import LittleMissHelpful.Storage.Storage;
import LittleMissHelpful.Tasks.TaskList;
import LittleMissHelpful.Ui.Ui;
import LittleMissHelpful.Exception.InvalidCommandException;
import LittleMissHelpful.Exception.InvalidTaskFormatException;
import LittleMissHelpful.Tasks.Task;

public class MarkTaskCommand extends Command {
    private final int taskIndex;

    public MarkTaskCommand(int i) {
        this.taskIndex = i - 1;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws InvalidCommandException {
        try {
            Task task = tasks.get(this.taskIndex);
            tasks.markTask(this.taskIndex);
            storage.save(tasks.getTasks());
            return ui.showMarkedTask(task);

        } catch (InvalidTaskFormatException e) {
            throw new InvalidCommandException("Task number out of range. Please provide a valid task number.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
