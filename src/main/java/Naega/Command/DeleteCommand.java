package Naega.Command;

import Naega.Command.Command;
import Naega.NaegaException;
import Naega.Storage.Storage;
import Naega.Task.Task;
import Naega.Task.TaskList;
import Naega.Ui.Ui;

public class DeleteCommand extends Command {
    private final int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex - 1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NaegaException {
        try {
            Task taskToDelete = tasks.getTask(taskIndex);
            tasks.deleteTask(taskIndex);
            ui.showDeletedTask(taskToDelete, tasks.size());
            storage.save(tasks.getTasks());
        } catch (IndexOutOfBoundsException e) {
            throw new NaegaException("Invalid task number.");
        }
    }

}