package denim.commands;

import denim.TaskList;
import denim.storage.TaskIo;

/**
 * Represents a find command that can be executed.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";
    public static final String USAGE = "find <taskDescription>";

    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }
    @Override
    public CommandResult execute(TaskList taskList, TaskIo taskIo) {
        TaskList resultList = taskList.findTasks(keyword);
        String returnMessage = String.format("Sure. Here are the matching tasks in your list:\n%s",
                resultList.printList());
        return new CommandResult(returnMessage);
    }

    public String getKeyword() {
        return keyword;
    }



    @Override
    public boolean isExit() {
        return false;
    }
}
