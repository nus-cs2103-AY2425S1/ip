package julie.command;
import julie.exception.JulieException;
import julie.task.Task;
import java.util.List;

public abstract class Command {
    public boolean isExit = false;
    public String commandString;

    public Command(String commandString) {
        this.commandString = commandString;
    }
    public abstract void run(List<Task> taskList) throws JulieException;
}
