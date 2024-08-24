public class MarkCommand extends Command{
	private int taskNumber;

	public MarkCommand(String input) throws HanaException {
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
		taskList.markTask(taskNumber, true);
		ui.printMarked(taskList.getTasks().get(taskNumber - 1),true);
		storage.save();
	}
}
