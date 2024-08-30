package julie.command;
import julie.misc.UI;
import julie.task.Task;
import java.util.List;

public class ByeCommand extends Command {
    public ByeCommand(String commandString) {
        super(commandString);
        this.isExit = true;
    }
    @Override
    public void run(List<Task> taskList) {
        UI.wrappedLinePrint("Bye!! See you soon!!");
    }
}
