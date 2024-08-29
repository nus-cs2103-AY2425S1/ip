package lexi.command;

import lexi.exception.LexiException;
import lexi.storage.Storage;
import lexi.task.Task;
import lexi.task.TaskList;
import lexi.ui.Ui;

public class DeleteCommand extends Command {
    private int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws LexiException {
        if(taskNumber <= 0 || taskNumber > tasks.getSize()) {
            throw new LexiException("You can't delete a task that doesn't exist");
        }
        Task removedTask = tasks.deleteTask(taskNumber-1);
        ui.showDeleteMessage(removedTask, tasks.getSize());
        storage.updateStorage(tasks.getTasks());
    }
}
