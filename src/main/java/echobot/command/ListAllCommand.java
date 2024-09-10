package echobot.command;

import echobot.exception.EchoBotException;
import echobot.task.Task;

import java.util.List;

public class ListAllCommand extends ListCommand {
    @Override
    public CommandResponse execute() throws EchoBotException {
        final List<Task> taskList = this.taskList.getTaskList();
        return super.getTaskListResponse(taskList);
    }
}
