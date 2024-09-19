package nen.commands;

import nen.tasks.Task;
import nen.utils.TaskList;

/**
 *  This class represents a command which find tasks with specified keyword
 * @author Gan Ren Yick (A0276246X)
 */
public class FindCommand extends Command {
    private String keyword;
    /**
     * Instantiates FindCommand
     * @param name of the command
     */
    public FindCommand(String name, String arg) {
        this.name = name;
        keyword = arg;
    }

    /**
     * Executes the command
     * @param taskList to be manipulated
     */
    @Override
    public void execute(TaskList taskList) {
        try {
            int count = 1;
            description += "Here are the matching tasks in your list:\n";
            for (Task t : taskList.findTaskWithKeyword(keyword)) {
                description += count + "." + t.toString() + "\n";
                count++;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            description = "find what?";
        }
    }
}
