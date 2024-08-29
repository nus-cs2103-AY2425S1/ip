package Denim.Commands;

import Denim.Storage.TaskIO;
import Denim.TaskList;

public class FindCommand extends Command {

    public String keyword;
    public static final String COMMAND_WORD = "find";
    public static final String USAGE = "find <taskDescription>";

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }
    @Override
    public CommandResult execute(TaskList taskList, TaskIO taskIO) {
        TaskList resultList = taskList.findTasks(keyword);
        String returnMessage = String.format("Sure. Here are the matching tasks in your list:\n%s",
                resultList.printList());
        return new CommandResult(returnMessage);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
