public abstract class Command {

	protected Command() {

	}

	public void execute(TaskList taskList, Ui ui, Storage storage) throws WigglyException {
		throw new WigglyException("This command is not supported yet.");
	}

	public boolean isExit() {
		return false;
	}
}
