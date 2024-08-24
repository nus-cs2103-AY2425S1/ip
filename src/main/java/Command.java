public abstract class Command {

	public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws HanaException;

	public boolean isExit() {
		return false;
	}
}
