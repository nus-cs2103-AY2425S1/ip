package denim.commands;

import denim.TaskList;
import denim.storage.WriteTaskFile;

/**
 * Represents a find command that can be executed.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";
    public static final String COMMAND_USAGE = "find <taskDescription>";
    public static final String COMMAND_EXAMPLE = "find homework";

    private String[] keywords;

    public FindCommand(String[] keywords) {
        this.keywords = keywords;
    }
    @Override
    public CommandResult execute(TaskList taskList, WriteTaskFile writeTaskFile) {
        TaskList resultList = taskList.findTasks(keywords);
        String returnMessage;

        if (resultList.isEmpty()) {
            returnMessage = String.format("The swearch wesult for %s did not find any matching tasks UwU.\n",
                    getKeywords());
            return new CommandResult(returnMessage, CommandStatus.COMMAND_PARTIAL_FAILURE);
        } else {
            returnMessage = String.format("Swure. Here are the matching tasks in your list:\n%s",
                    resultList.printList());
            return new CommandResult(returnMessage, CommandStatus.COMMAND_SUCCESSFUL);
        }
    }

    public String getKeywords() {
        return String.join(" ", keywords);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
