public class helpCommand extends Command {

	@Override
	public void execute(TaskList taskList, Ui ui, Storage storage) throws HanaException {
		ui.printCommands();
	}
}
