package echobot.command;

import echobot.exception.EchoBotException;
import echobot.task.Task;

public class MarkCommand extends Command implements Undoable {
    public final static String COMMAND = "mark";
    private final CommandType commandType = CommandType.MARK;

    private final int index;
    private boolean lastMarkState;

    public MarkCommand(int index) {
        this.index = index - 1;
    }

    @Override
    public CommandResponse execute() throws EchoBotException {
        this.lastMarkState = taskList.getTaskList().get(this.index).isDone();
        Task task = taskList.markTaskByIndex(this.index);
        String response = "Nice! I've marked this task as done:\n\t\t\t\t" + task;
        fileManagement.save();
        return new CommandResponse(this.commandType, response);
    }

    @Override
    public CommandResponse undo() throws EchoBotException {
        final Command command = this.lastMarkState
                ? new MarkCommand(this.index + 1)
                : new UnMarkCommand(this.index + 1);
        command.setTaskList(super.taskList);
        command.setFileManagement(super.fileManagement);
        command.setCommandHistoryList(super.commandHistoryList);
        return command.execute();
    }
}
