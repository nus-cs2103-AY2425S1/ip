public class ByeCommand extends Command{

	@Override
	public void execute(TaskList taskList, Ui ui, Storage storage) throws HanaException {
		ui.printBye();
	}

	@Override
	public boolean isExit() {
		return true;
	}
}
