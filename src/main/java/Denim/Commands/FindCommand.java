package denim.commands;

import denim.TaskList;
import denim.storage.WriteTaskFile;

/**
 * Represents a find command that can be executed.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";
    public static final String USAGE = "find <taskDescription>";

    private String[] keywords;

    public FindCommand(String[] keywords) {
        this.keywords = keywords;
    }
    @Override
    public CommandResult execute(TaskList taskList, WriteTaskFile writeTaskFile) {
        TaskList resultList = taskList.findTasks(keywords);
        String returnMessage = String.format("Sure. Here are the matching tasks in your list:\n%s",
                resultList.printList());
        return new CommandResult(returnMessage);
    }

    public String[] getKeyword() {
        return keywords;
    }



    @Override
    public boolean isExit() {
        return false;
    }
}
