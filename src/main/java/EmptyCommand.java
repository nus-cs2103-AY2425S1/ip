import java.util.HashMap;

public class EmptyCommand extends Command {

    public EmptyCommand(HashMap<String, String> args) {
        super(args);
    }

    @Override
    public boolean isExitCommand() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) {
        System.out.println("you forgot to input");
    }

}
