package hana.command;

import hana.HanaException;
import hana.storage.Storage;
import hana.task.Task;
import hana.tasklist.TaskList;
import hana.ui.Ui;

/**
 * Represents a command to delete task.
 * When executed, this command will remove task, print a message and save.
 */
public class DeleteCommand extends Command{
	private int taskNumber;

	/**
	 * Constructs a new DeleteCommand with taskNumber.
	 *
	 * @param input The input from user.
	 * @throws HanaException If an error occurs.
	 */
	public DeleteCommand(String input) throws HanaException {
		String[] parts = input.split(" ", 2);
		if (parts.length < 2 || parts[1].trim().isEmpty()) {
			throw new HanaException("Task number cannot be empty.");
		}
		this.taskNumber = Integer.parseInt(parts[1].trim());
	}

	/**
	 * Executes the command to delete task.
	 *
	 * @param taskList The list of tasks.
	 * @param ui The UI object used to interact with the user.
	 * @param storage The storage object to handle reading/writing of tasks.
	 * @throws HanaException If an error occurs during command execution.
	 */
	@Override
	public void execute(TaskList taskList, Ui ui, Storage storage) throws HanaException {
		if (taskList.isEmpty()) {
			throw new HanaException("No tasks added yet. Add a task first!");
		}
		if (taskNumber < 1 || taskNumber > taskList.getTasks().size()) {
			throw new HanaException("Invalid task number! Task number must be between 1 and " + taskList.getTasks().size() + ".");
		}
		Task deletedTask = taskList.getTasks().get(taskNumber - 1);
		taskList.deleteTask(taskNumber);
		ui.printDeleted(deletedTask, taskList.getTasks().size());
		storage.save();
	}
}
