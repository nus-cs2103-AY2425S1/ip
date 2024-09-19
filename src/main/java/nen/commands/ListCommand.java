package nen.commands;

import nen.utils.TaskList;

/**
 *  This class represents a command which print out all the tasks
 * @author Gan Ren Yick (A0276246X)
 */
public class ListCommand extends Command {
    /**
     * Instantiates ListCommand
     * @param name of the command
     */
    public ListCommand(String name) {
        this.name = name;
    }

    /**
     * Executes the command
     * @param taskList to be manipulated
     */
    @Override
    public void execute(TaskList taskList) {
        description += "Here are the tasks in your list:\n"
                + taskList.toString();
    }

}
