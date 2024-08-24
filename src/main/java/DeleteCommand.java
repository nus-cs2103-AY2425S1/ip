public class DeleteCommand extends Command{
	private int taskNumber;

	public DeleteCommand(String input) throws HanaException {
		String[] parts = input.split(" ", 2);
		if (parts.length < 2 || parts[1].trim().isEmpty()) {
			throw new HanaException("Task number cannot be empty.");
		}
		this.taskNumber = Integer.parseInt(parts[1].trim());
	}

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
