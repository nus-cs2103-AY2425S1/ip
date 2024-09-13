package lexi.command;

import lexi.exception.LexiException;
import lexi.parser.Parser;
import lexi.storage.Storage;
import lexi.task.Task;
import lexi.task.TaskList;
import lexi.ui.Ui;

/**
 * Represents a command to update a task in the task list.
 * The command parses a new description and updates the specified task.
 */
public class UpdateCommand extends Command {
    private final int taskNumber;
    private final String updateDescription;
    private String response;

    /**
     * Constructs an UpdateCommand with the specified description and task number.
     *
     * @param updateDescription The new description to update the task with.
     * @param taskNumber The number of the task to be updated in the task list.
     */
    public UpdateCommand(String updateDescription, int taskNumber) {
        this.updateDescription = updateDescription;
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the update command. Parses the new task description, updates the task at the specified
     * task number, and updates the storage with the new task list.
     *
     * @param tasks The list of tasks.
     * @param ui The UI component to display messages to the user.
     * @param storage The storage component to save the updated task list.
     * @throws LexiException If the specified task number does not exist or if there is an error updating the task.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws LexiException {
        if (this.taskNumber > tasks.getSize() - 1) {
            throw new LexiException("Sorry! That task does not exist.\nPlease key in the correct task number");
        }
        Command newCommand = Parser.parse(updateDescription);
        newCommand.execute(tasks, ui, storage);
        Task updatedTask = tasks.deleteTask(tasks.getSize() - 1);
        tasks.updateTask(updatedTask, taskNumber);
        storage.updateStorage(tasks.getTasks());
        response = ui.showUpdateMessage(this.taskNumber + 1, updatedTask);
    }

    /**
     * Returns the name of this command.
     *
     * @return The string "UPDATE".
     */
    @Override
    public String getCommandName() {
        return "UPDATE";
    }

    /**
     * Returns the response message generated after executing the update command.
     *
     * @return The response message as a String.
     */
    @Override
    public String getString() {
        return this.response;
    }
}
