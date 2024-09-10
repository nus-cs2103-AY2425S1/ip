package echobot.command;

import echobot.exception.EchoBotException;
import echobot.task.Task;

public class DeleteCommand extends Command implements Undoable {
    public final static String COMMAND = "delete";
    private final int index;
    private final CommandType commandType = CommandType.DELETE;
    private Task taskDeleted;

    public DeleteCommand(int index) {
        this.index = index - 1;
    }

    @Override
    public CommandResponse execute() throws EchoBotException {
        this.taskDeleted = taskList.deleteTaskByIndex(this.index);
        String response = "Noted. I've removed this task: \n\t\t\t\t" + this.taskDeleted + "\n\t\t\tNow you have " + taskList.size() + " task(s) in the list.";
        fileManagement.save();
        return new CommandResponse(this.commandType, response);
    }

    @Override
    public CommandResponse undo() throws EchoBotException {
        final AddCommand addCommand = new AddCommand(this.taskDeleted, this.index);
        addCommand.setTaskList(super.taskList);
        addCommand.setFileManagement(super.fileManagement);
        addCommand.setCommandHistoryList(super.commandHistoryList);
        return addCommand.execute();
    }
}
