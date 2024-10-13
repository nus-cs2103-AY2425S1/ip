package echobot.command;

import echobot.exception.EchoBotException;
import echobot.task.Task;

import java.util.List;

/**
 * Represents a list command.
 */
public abstract class ListCommand extends Command {
    public final static String COMMAND = "list";
    private final CommandType commandType = CommandType.LIST;


    @Override
    public abstract CommandResponse execute() throws EchoBotException;

    /**
     * Generates task list response.
     *
     * @param taskList The list of task.
     * @return CommandResponse.
     */
    public CommandResponse getTaskListResponse(List<Task> taskList) {
        StringBuilder output = new StringBuilder();
        output.append("Here are the tasks in your list:\n\t\t\t");
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            output.append(i + 1);
            output.append(".");
            output.append(task);
            output.append("\n\t\t\t");
        }

        if (!output.isEmpty()) {
            output.delete(output.length() - 4, output.length());
        }
        return new CommandResponse(this.commandType, output.toString());
    }
}
