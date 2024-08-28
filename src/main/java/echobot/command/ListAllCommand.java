package echobot.command;

import echobot.exception.EchoBotException;

public class ListAllCommand extends ListCommand {
    @Override
    public CommandResponse execute() throws EchoBotException {
        return super.getTaskListResponse(taskList.getTaskList());
    }
}
