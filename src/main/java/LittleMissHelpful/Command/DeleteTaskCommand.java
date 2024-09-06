package LittleMissHelpful.Command;

import LittleMissHelpful.Storage.Storage;
import LittleMissHelpful.Tasks.TaskList;
import LittleMissHelpful.Ui.Ui;
import LittleMissHelpful.Exception.InvalidCommandException;
import LittleMissHelpful.Exception.InvalidTaskFormatException;
import LittleMissHelpful.Tasks.Task;

public class DeleteTaskCommand extends Command {
    private final int taskIndex;

    public DeleteTaskCommand(int listIndex) {
        this.taskIndex = listIndex - 1;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws InvalidCommandException {
        try {
            Task task = tasks.get(taskIndex);
            tasks.delete(taskIndex);
            storage.save(tasks.getTasks());
            return (ui.showDeletedTask(task, tasks));

        } catch (InvalidTaskFormatException e) {
            throw new InvalidCommandException("Task number out of range. Please provide a valid task number.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
