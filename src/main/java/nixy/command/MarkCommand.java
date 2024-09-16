package nixy.command;

import nixy.Storage;
import nixy.task.TaskList;
import nixy.ui.Ui;

/**
 * Class representing the command to mark/unmark a task as done.
 */
public class MarkCommand implements UndoableCommand {
    private Ui ui;
    private TaskList tasks;
    private Storage storage;
    private int taskNumber;
    private boolean isMark;

    /**
     * Constructor for MarkCommand.
     *
     * @param ui The Ui object to interact with the user.
     * @param tasks The TaskList object to store tasks.
     * @param storage The Storage object to store tasks data.
     * @param taskNumber The task number to mark/unmark as done.
     * @param isMark The boolean to indicate if the task is to be marked or unmarked.
     */
    public MarkCommand(Ui ui, TaskList tasks, Storage storage, int taskNumber, boolean isMark) {
        this.ui = ui;
        this.tasks = tasks;
        this.storage = storage;
        this.taskNumber = taskNumber;
        this.isMark = isMark;
    }

    /**
     * Returns the task number to mark/unmark as done.
     *
     * @return The task number to mark/unmark as done.
     */
    public int getTaskNumber() {
        return taskNumber;
    }

    /**
     * Returns the type of command depending on whether the task is to be marked or unmarked.
     *
     * @return CommandType.MARK or CommandType.UNMARK
     */
    @Override
    public CommandType getType() {
        return isMark ? CommandType.MARK : CommandType.UNMARK;
    }

    /**
     * Executes the command to mark/unmark a task as done.
     */
    @Override
    public void execute() {
        if (isMark) {
            String taskStr = tasks.markTaskAsDone(taskNumber);
            ui.showMarkedAsDone(taskStr);
        } else {
            String taskStr = tasks.markTaskAsUndone(taskNumber);
            ui.showMarkedAsUndone(taskStr);
        }
        storage.save(tasks);
    }

    /**
     * Undoes the command to mark/unmark a task as done.
     */
    @Override
    public void undo() {
        if (isMark) {
            String taskStr = tasks.markTaskAsUndone(taskNumber);
            ui.showMarkedAsUndone(taskStr);
        } else {
            String taskStr = tasks.markTaskAsDone(taskNumber);
            ui.showMarkedAsDone(taskStr);
        }
        storage.save(tasks);
    }
}
