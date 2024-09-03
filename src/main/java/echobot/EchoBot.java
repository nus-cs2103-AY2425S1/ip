package echobot;

import echobot.command.Command;
import echobot.command.CommandParser;
import echobot.command.CommandResponse;
import echobot.exception.EchoBotException;
import echobot.io.FileManagement;
import echobot.task.TaskList;

public class EchoBot {
    private final FileManagement fileManagement;
    private final TaskList taskList;

    public EchoBot() {
        this.fileManagement = new FileManagement();
        this.taskList = fileManagement.load();
    }

    public CommandResponse generateResponseFromUserCommand(String input) throws EchoBotException {
        Command command = new CommandParser().parse(input);
        command.setTaskList(this.taskList);
        command.setFileManagement(this.fileManagement);
        return command.execute();
    }
}
