package lolo.command;

import lolo.Ui;
import lolo.LoloException;
import lolo.storage.Storage;
import lolo.task.Task;
import lolo.task.TaskList;

class DeleteCommand extends Command {
    private int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws LoloException {
        Task task = tasks.deleteTask(taskNumber);
        ui.showDeletedTask(task, tasks.size());
        storage.save(tasks.getTasks());
    }
}