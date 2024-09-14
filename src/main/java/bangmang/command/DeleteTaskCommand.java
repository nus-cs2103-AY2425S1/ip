package bangmang.command;

import bangmang.storage.Storage;
import bangmang.tasks.TaskList;
import bangmang.ui.Ui;
import bangmang.exception.InvalidCommandException;
import bangmang.exception.InvalidTaskFormatException;
import bangmang.tasks.Task;

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
