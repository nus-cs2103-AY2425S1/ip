package command;

import exception.EchoBotException;

public class ListAllCommand extends ListCommand {
    @Override
    public CommandResponse execute() throws EchoBotException {
        return super.getTaskListResponse(taskList.getTaskList());
    }
}
