import java.io.IOException;
public abstract class Command {
    public abstract void execute(TaskList tasks, Storage storage, Ui ui) throws IOException;
    public boolean isExit() {
        return false;
    }
}