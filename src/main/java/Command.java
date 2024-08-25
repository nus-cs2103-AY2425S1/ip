import java.util.HashMap;

public abstract class Command {
    private HashMap<String, String> args;

    public Command(HashMap<String, String> args) {
        this.args = args;
    }

    public HashMap<String, String> getArgs() {
        return this.args;
    }

    public abstract boolean isExitCommand();

    public abstract void execute(TaskList tasks, Storage storage);
}
