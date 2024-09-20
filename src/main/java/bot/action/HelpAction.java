package bot.action;

import bot.enums.Command;
import bot.tasks.TaskList;

/**
 * Action to list all commands.
 *
 * @author mongj
 */
public class HelpAction extends Action {
    /**
     * Displays a list of available commands and their usages.
     *
     * @param taskList not used. This is kept for consistency with other actions
     * @return a string containing the list of commands and their usages.
     */
    @Override
    public String execute(TaskList taskList) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Command command : Command.values()) {
            stringBuilder.append(command.getHelpMessage()).append("\n\n");
        }
        return stringBuilder.toString();
    }
}
