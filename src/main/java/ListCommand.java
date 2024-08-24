public class ListCommand extends Command {

	@Override
	public void execute(TaskList taskList, Ui ui, Storage storage) throws HanaException {
		ui.printTasks(taskList.getTasks());
	}
}
