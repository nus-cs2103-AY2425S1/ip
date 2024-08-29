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

    /**
     * Executes the command from users.
     *
     * @return The result of the command after being executed.
     * @throws EchoBotException If there is any error during the execution.
     */
    public CommandResponse execute() throws EchoBotException {
        return null;
    }
}
