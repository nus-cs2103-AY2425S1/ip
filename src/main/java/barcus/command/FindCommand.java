package barcus.command;

import barcus.storage.Storage;
import barcus.tasklist.TaskList;

/**
 * Command to find tasks w certain string in description
 */
public class FindCommand extends Command {
    private String toFind;

    /**
     * Constructor
     * @param toFind String for command to find
     */
    public FindCommand(String toFind) {
        this.toFind = toFind;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) {
        TaskList tasksSubstring = tasks.findTask(toFind);
        tasksSubstring.showTaskList();
        output = "Here are the matching tasks!\n" + tasksSubstring.getTaskListDisplay();
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
