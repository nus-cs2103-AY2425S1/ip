package hana.command;

import hana.HanaException;
import hana.storage.Storage;
import hana.task.Task;
import hana.task.ToDo;
import hana.tasklist.TaskList;
import hana.ui.Ui;

public class ToDoCommand extends Command{
	private String description;

	public ToDoCommand(String input) throws HanaException {
		this.description = input.substring(4).trim();
		if (description.isEmpty()) {
			throw new HanaException("ToDo task must have a description.");
		}
	}

	@Override
	public void execute(TaskList taskList, Ui ui, Storage storage) throws HanaException {
		Task task = new ToDo(description);
		taskList.addTask(task);
		ui.printAdd(task, taskList.getTasks().size());
		storage.save();
	}
}
