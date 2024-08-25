package hana.command;

import hana.tasklist.TaskList;
import hana.task.Task;
import hana.task.Deadline;
import hana.ui.Ui;
import hana.storage.Storage;
import hana.HanaException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a command to add deadline task.
 * When executed, this command will add new deadline task, print a message and save.
 */
public class DeadlineCommand extends Command{
	private String description;
	private LocalDateTime deadline;

	/**
	 * Constructs a new DeadlineCommand with a description and due date.
	 *
	 * @param input The input from user.
	 * @throws HanaException If an error occurs.
	 */
	public DeadlineCommand(String input) throws HanaException {
		try {
			String[] parts = input.substring(8).split(" /by ");
			if (parts.length < 2) {
				throw new HanaException("Deadline task must have a description and a due date.");
			}
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
			this.deadline = LocalDateTime.parse(parts[1].trim(), formatter);
			this.description = parts[0].trim();
		} catch (DateTimeParseException e) {
			throw new HanaException("Please provide a valid deadline command in the format: " +
					"deadline [description] /by [d/M/yyyy HHmm]");
		}
	}

	/**
	 * Executes the command to add Deadline task.
	 *
	 * @param taskList The list of tasks.
	 * @param ui The UI object used to interact with the user.
	 * @param storage The storage object to handle reading/writing of tasks.
	 * @throws HanaException If an error occurs during command execution.
	 */
	@Override
	public void execute(TaskList taskList, Ui ui, Storage storage) throws HanaException {
		Task task = new Deadline(description, deadline);
		taskList.addTask(task);
		ui.printAdd(task, taskList.getTasks().size());
		storage.save();
	}
}
