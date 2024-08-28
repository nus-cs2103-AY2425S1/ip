package echobot.command;

import echobot.exception.EchoBotException;
import echobot.io.FileManagement;
import echobot.task.TaskList;

public class Command {
    protected FileManagement fileManagement;
    protected TaskList taskList;

    public void setTaskList(TaskList taskList) {
        this.taskList = taskList;
    }

    public void setFileManagement(FileManagement fileManagement) {
        this.fileManagement = fileManagement;
    }

    public CommandResponse execute() throws EchoBotException {
        return null;
    }
}
