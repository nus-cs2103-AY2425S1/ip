package action;

import enums.Command;
import task.TaskList;

/**
 * Action to list all commands.
 *
 * @author dwsc37
 */
public class HelpAction extends Action {
    /**
     * Returns the list of commands and their usages.
     * @param taskList The task list to be mutated, unused.
     * @return List of commands and their usages.
     */
    @Override
    public String execute(TaskList taskList) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Command command : Command.values()) {
            stringBuilder.append(command.getHelpMessage()).append("\n");
        }
        return stringBuilder.toString();
    }
}
