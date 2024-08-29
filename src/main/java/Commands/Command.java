package Commands;
import TaskList.TaskList;
import Messages.ReturnMessage;
import java.io.FileWriter;

public class Command {
    private TaskList taskList;
    private String[] additionalInput;

    public Command(String[] additionalInput) {
        this.additionalInput = additionalInput;
    }

    public void setData(TaskList taskList) {
        this.taskList = taskList;
    }

    public ReturnMessage execute() {
        throw new UnsupportedOperationException("You cannot execute a generic command, command must have a type.");
    }
}
