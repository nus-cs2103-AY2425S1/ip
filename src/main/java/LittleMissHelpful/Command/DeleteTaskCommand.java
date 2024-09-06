package LittleMissHelpful.Command;

import LittleMissHelpful.Storage;
import LittleMissHelpful.TaskList;
import LittleMissHelpful.Ui;
import LittleMissHelpful.Exception.InvalidCommandException;
import LittleMissHelpful.Exception.InvalidTaskFormatException;
import LittleMissHelpful.Task.Task;

public class DeleteTaskCommand extends Command {
    private final int taskIndex;

    public DeleteTaskCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidCommandException {
        try {
            Task task = tasks.get(taskIndex);
            tasks.delete(taskIndex);
            ui.showDeletedTask(task, tasks);
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
