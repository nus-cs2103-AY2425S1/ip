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

public class DeadlineCommand extends Command{
	private String description;
	private LocalDateTime deadline;


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

	@Override
	public void execute(TaskList taskList, Ui ui, Storage storage) throws HanaException {
		Task task = new Deadline(description, deadline);
		taskList.addTask(task);
		ui.printAdd(task, taskList.getTasks().size());
		storage.save();
	}
}
