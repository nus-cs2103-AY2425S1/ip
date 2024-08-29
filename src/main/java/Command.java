public abstract class Command {

    protected abstract void execute(TaskList taskList, Ui ui, Storage storage) throws TheBotFatherException;

    protected boolean isExit() {
        return false;
    }

    ;
}
