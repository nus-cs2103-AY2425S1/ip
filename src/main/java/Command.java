import java.io.IOException;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws SammyException, IOException;

    public boolean isExit() {
        return false;
    }
}

