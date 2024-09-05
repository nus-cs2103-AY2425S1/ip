package nen.commands;

import nen.utils.TaskList;

/**
 *  This class represents a end command that do nothing
 * @author Gan Ren Yick (A0276246X)
 */
public class DoNothingCommand extends Command {

    /**
     * Instantiate DoNothingCommand
     * @param name of the command
     */
    public DoNothingCommand(String name) {
        this.name = name;
    }

    /**
     * Execute the command
     * @param taskList to be manipulated
     */
    @Override
    public void execute(TaskList taskList) {
        return;
    }

}
