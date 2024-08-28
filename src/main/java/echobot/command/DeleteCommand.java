package echobot.command;

import echobot.exception.EchoBotException;
import echobot.task.Task;

public class DeleteCommand extends Command {
    public final static String COMMAND = "delete";
    private final int index;

    public DeleteCommand(int index) {
        this.index = index - 1;
    }

    @Override
    public CommandResponse execute() throws EchoBotException {
        final Task task = taskList.deleteTaskByIndex(this.index);
        String response = "Noted. I've removed this task: \n\t\t\t\t" + task + "\n\t\t\tNow you have " + taskList.size() + " task(s) in the list.";
        fileManagement.save();
        return new CommandResponse(response);
    }
}
