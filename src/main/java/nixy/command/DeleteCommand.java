package nixy.command;

import nixy.ui.Ui;
import nixy.task.TaskList;
import nixy.Storage;

/**
 * Class representing the command to delete a task.
 */
public class DeleteCommand implements Command {
    private Ui ui;
    private TaskList tasks;
    private int taskNumber;
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
        String taskStr = tasks.deleteTask(taskNumber);
        ui.showDeletedTask(taskStr, tasks.getTaskCount());
        storage.save(tasks);
    }
}
