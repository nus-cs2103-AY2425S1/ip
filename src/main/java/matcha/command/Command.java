package matcha.command;
import matcha.exception.MatchaException;
import matcha.Storage;
import matcha.TaskList;
import matcha.Ui;

public abstract class Command {

    private boolean isExit = false;

    public void setExit(boolean exit) {
        isExit = exit;
    }

    public boolean isExit() {
        return isExit;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws MatchaException;

}
