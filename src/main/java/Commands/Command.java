package Commands;
import TaskList.TaskList;
import Messages.ReturnMessage;

import java.nio.file.Path;

public class Command {
    protected TaskList taskList;
    protected String[] additionalInput;

    public Command(String[] additionalInput) {
        this.additionalInput = additionalInput;
    }

    public void setData(TaskList taskList) {
        this.taskList = taskList;
    }

    public boolean isExit() {
        return false;
    }

    public void write(Path filePath) {
    }

    public ReturnMessage execute() {
        return new ReturnMessage("  ~  Sorry, that's not something I know how to do :(",
                "  ~  Please specify either a To Do, a Deadline or an Event!");
    }
}
