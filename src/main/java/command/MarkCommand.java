package command;

import exception.EchoBotException;
import task.Task;

public class MarkCommand extends Command {
    public final static String COMMAND = "mark";

    private final int index;

    public MarkCommand(int index) {
        this.index = index - 1;
    }

    @Override
    public CommandResponse execute() throws EchoBotException {
        Task task = taskList.markTaskByIndex(this.index);
        String response = "Nice! I've marked this task as done:\n\t\t\t\t" + task;
        fileManagement.save();
        return new CommandResponse(response);
    }
}
