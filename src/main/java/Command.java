import java.io.IOException;

public abstract class Command {

    public enum CommandType {
        ADD,
        DELETE,
        EXIT,
        LIST,
        HELP,
        MARK,
        UNMARK
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws SnipeException, IOException;

    public boolean isExit() {
        return false;  // Default behavior is that the command is not an exit command
    }
}