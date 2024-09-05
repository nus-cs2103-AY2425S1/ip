package main.LittleMissHelpful.Command;

import main.LittleMissHelpful.Exception.InvalidCommandException;
import main.LittleMissHelpful.Exception.InvalidTaskFormatException;
import main.LittleMissHelpful.Task.Task;
import main.LittleMissHelpful.TaskList;
import main.LittleMissHelpful.Ui;
import main.LittleMissHelpful.Storage;

public class UnmarkTaskCommand extends Command {
    private final int taskIndex;

    public UnmarkTaskCommand(int i) {
        this.taskIndex = i - 1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidCommandException {
        try {
            Task task = tasks.get(this.taskIndex);
            tasks.unmarkTask(this.taskIndex);
            ui.showUnmarkedTask(task);
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
