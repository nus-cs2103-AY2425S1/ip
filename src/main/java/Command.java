import java.io.IOException;

public abstract class Command {
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws JoeException;
    public boolean isExit() {
        return false;
    }

}
