import java.io.IOException;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws JerielException, IOException;

    public boolean isExit() {
        return false; // Default behavior is that the command doesn't exit the program
    }
}
