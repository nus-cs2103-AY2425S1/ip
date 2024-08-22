package action;

import enums.Command;
import task.TaskList;

public class HelpAction extends Action {
    @Override
    public String execute(TaskList taskList) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Command command : Command.values()) {
            stringBuilder.append(command.getHelpMessage()).append("\n");
        }
        return stringBuilder.toString();
    }
}
