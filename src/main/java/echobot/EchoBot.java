package echobot;

import echobot.command.Command;
import echobot.command.CommandParser;
import echobot.command.CommandResponse;
import echobot.command.Undoable;
import echobot.exception.EchoBotException;
import echobot.io.FileManagement;
import echobot.task.TaskList;

import java.util.Stack;

public class EchoBot {
    private final FileManagement fileManagement;
    private final TaskList taskList;
    private final Stack<Undoable> commandHistoryList;

    public EchoBot() {
        this.fileManagement = new FileManagement();
        this.taskList = fileManagement.load();
        this.commandHistoryList = new Stack<>();
    }

    public CommandResponse generateResponseFromUserCommand(String input) throws EchoBotException {
        Command command = new CommandParser().parse(input);
        command.setTaskList(this.taskList);
        command.setFileManagement(this.fileManagement);
        command.setCommandHistoryList(this.commandHistoryList);
        if (command instanceof Undoable) {
            this.commandHistoryList.add((Undoable) command);
        }
        return command.execute();
    }
}
