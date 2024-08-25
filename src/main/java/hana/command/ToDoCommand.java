package hana.command;

import hana.HanaException;
import hana.storage.Storage;
import hana.task.Task;
import hana.task.ToDo;
import hana.tasklist.TaskList;
import hana.ui.Ui;

/**
 * Represents a command to add ToDo task.
 * When executed, this command will add new ToDo task, print a message and save.
 */
public class ToDoCommand extends Command{
	private String description;

	/**
	 * Constructs a new ToDoCommand with a description, start and end date.
	 *
	 * @param input The input from user.
	 * @throws HanaException If an error occurs.
	 */
	public ToDoCommand(String input) throws HanaException {
		this.description = input.substring(4).trim();
		if (description.isEmpty()) {
			throw new HanaException("ToDo task must have a description.");
		}
	}

	/**
	 * Executes the command to add ToDo task.
	 *
	 * @param taskList The list of tasks.
	 * @param ui The UI object used to interact with the user.
	 * @param storage The storage object to handle reading/writing of tasks.
	 * @throws HanaException If an error occurs during command execution.
	 */
	@Override
	public void execute(TaskList taskList, Ui ui, Storage storage) throws HanaException {
		Task task = new ToDo(description);
		taskList.addTask(task);
		ui.printAdd(task, taskList.getTasks().size());
		storage.save();
	}
}
