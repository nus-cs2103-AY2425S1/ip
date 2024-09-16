package nixy.command;

import nixy.Storage;
import nixy.task.Task;
import nixy.task.TaskList;
import nixy.ui.Ui;

/**
 * Class representing the command to delete a task.
 */
public class DeleteCommand implements UndoableCommand {
    private Ui ui;
    private TaskList tasks;
    private int taskNumber;
    private Task deletedTask;
    private Storage storage;

    /**
     * Constructor for DeleteCommand.
     *
     * @param ui The Ui object to interact with the user.
     * @param tasks The TaskList object to store tasks.
     * @param taskNumber The task number to delete.
     */
    public DeleteCommand(Ui ui, TaskList tasks, int taskNumber, Storage storage) {
        this.ui = ui;
        this.tasks = tasks;
        this.taskNumber = taskNumber;
        this.storage = storage;
    }

    /**
     * Returns the task number to delete.
     *
     * @return The task number to delete.
     */
    public int getTaskNumber() {
        return taskNumber;
    }

    /**
     * Returns the type of command.
     *
     * @return CommandType.DELETE
     */
    @Override
    public CommandType getType() {
        return CommandType.DELETE;
    }

    /**
     * Executes the command to delete a task.
     */
    @Override
    public void execute() {
        deletedTask = tasks.deleteTask(taskNumber);
        ui.showDeletedTask(deletedTask.toString(), tasks.getTaskCount());
        storage.save(tasks);
    }

    /**
     * Undoes the command to delete a task.
     */
    @Override
    public void undo() {
        assert deletedTask != null : "Deleted task should not be null";
        tasks.addTask(taskNumber, deletedTask);
        ui.showAddedTask(deletedTask, tasks.getTaskCount());
        storage.save(tasks);
    }
}
