import java.util.List;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    public ListCommand() {
    }

    @Override
    public boolean executeCommand() {
        Utilities.OutlineMessage("These are your current tasks!\n" + Command.taskList.toString());
        return true;
    }
}
