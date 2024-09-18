package jude.command;

import jude.JudeException;
import jude.Storage;
import jude.TaskList;

/**
 * Represents the command which contains the instruction of series of actions to find a particular keyword
 * from the existing Tasklist.
 */
public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Finds the tasks in the TaskList. Displays the taskList onto the ui.
     * @param list that will return the string representation of the tasklist
     * @param storage
     * @throws JudeException if any one of the method call fails
     */
    @Override
    public void execute(TaskList list, Storage storage) {
        setMessage("Here are the matching tasks in your list: \n" + list.search(keyword));
    }
}
